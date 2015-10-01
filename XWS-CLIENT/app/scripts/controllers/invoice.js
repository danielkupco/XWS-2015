'use strict';

angular.module('invoice', [
	'resource.invoice',
	'ui.bootstrap',
	'invoiceItem',
	'resource.invoiceItem'])

.controller('invoiceCtrl', function (Invoice, $http, $scope, $routeParams, $rootScope, $modal, $log, $location) {
	//ako pozivamo edit postojece fakture
	if($routeParams.invoiceId!='new'){
		//preuzimanje parametra iz URL
		var invoiceId = $routeParams.invoiceId;
		
		//preuzimanje fakure sa servera. Posto smo u Invoice factory rutu definisali kao '...invoice/:invoiceId' invoiceId ce se proslediti kao parametar rute na server 
		Invoice.get({'invoiceId':invoiceId, 'url_kupca':$rootScope.url_kupca, 'pib_dob':$rootScope.pib_dob}).$promise.then(function (data) {
			$scope.invoice = data;
			if(data.Stavka){
				$scope.invoice.Stavka = data.Stavka;
			}else{
				$scope.invoice.Stavka = [];
			}
		});
	}
	//ako kreiramo novu fakutru
	else{
		$scope.invoice = new Invoice();
		$scope.invoice.Stavka = [];
	}
	//funkcija koja otvara datepicker
	$scope.openDatepicker = function($event, opened) {
		$event.preventDefault();
		$event.stopPropagation();
		$scope[opened] = true;
	};

	//modalni dijalog za stavku fakutre
	$scope.openModal = function (invoiceItem, size) {

		var modalInstance = $modal.open({
			templateUrl: 'views/invoice-item.html',
			controller: 'invoiceItemCtrl',
			size: size,
			resolve: {
				invoiceItem: function () {
					return invoiceItem;
				}
			}
		});
		modalInstance.result.then(function (data) {
			var invoiceItem = data.invoiceItem;
			//ako stavka fakture nema id i ako je akcija 'save' znaci da je nova i dodaje se u listu. ako ima, svakako se manja u listi
			if(!invoiceItem.Redni_broj && data.action==='save'){
				$scope.invoice.Stavka.push(invoiceItem);				
			}
			//ako stavka treba da se obrise izbaci se iz niza
			if(data.action==='delete'){
				var index = $scope.invoice.Stavka.indexOf(invoiceItem);
				$scope.invoice.Stavka.splice(index, 1);
				}
		});
	};

	//cuvanje izmena
	$scope.save = function () {
		if(!$scope.invoice.id){		 		//ako stavka nema id => ne postoji u bazi => cuvamo je celu 
			// podrzani nacini pozivanja resource funkcije

			/*Invoice.save({'url_kupca':$rootScope.url_kupca, 'pib_dob':$rootScope.pib_dob}, $scope.invoice, function(data){
				$log.info(data);
				$log.info($scope.invoice.id);
				$location.path('/invoice-list');
			}).$promise.then(function(data){
				$log.info(data);
				$log.info($scope.invoice.id);
				$location.path('/invoice-list');
			});

			Invoice.save({'url_kupca':$rootScope.url_kupca, 'pib_dob':$rootScope.pib_dob}, $scope.invoice, function(data){
				$log.info(data);
				$log.info($scope.invoice.id);
				$location.path('/invoice-list');
			});*/

			$scope.invoice.$save({'url_kupca':$rootScope.url_kupca, 'pib_dob':$rootScope.pib_dob}, function(data, status){
				$log.info(data);
				var location = status('Location');
				$log.info('location: ' + location);
				var last = location.lastIndexOf('/');
				var id = location.substring(last + 1);
				$scope.invoice.id = id;
				$location.path('/invoice/' + id);
			});

			/*$scope.invoice.$save({'url_kupca':$rootScope.url_kupca, 'pib_dob':$rootScope.pib_dob}).then(function(data){
				$log.info(data);
				$log.info($scope.invoice.id);
				$location.path('/invoice-list');
			});*/
		}
		$log.info('save');
	};

	$scope.delete = function () {	//brisanje fakture nije u specifikaciji
		/*if($scope.invoice.id){
			$scope.invoice.$delete({url_kupca:$rootScope.url_kupca, pib_dob:$rootScope.pib_dob, invoiceId:$scope.invoice.id}, function () {
				console.log('delete');
			});
		}*/
	};


	// item order by
	$scope.itemPredicate = 'Vrednost';
	$scope.itemReverse = true;
	$scope.itemOrder = function(predicate) {
		$scope.itemReverse = ($scope.itemPredicate == predicate) ? !$scope.itemReverse : false;
		$scope.itemPredicate = predicate;
	};

	$scope.lowerComparator = function (actual, expected) {
		if(expected != '') {
			return actual < expected;
		}
		else return true;
	};

	$scope.equalComparator = function (actual, expected) {
		if(expected != '') {
			return actual == expected;
		}
		else return true;
	};

	$scope.greaterComparator = function (actual, expected) {
		if(expected != '') {
			return actual > expected;
		}
		else{
			return true;
		}
	};


	$scope.send = function(){
		$http({
			method : 'GET',
			url : 'http://localhost:8080/xws/api/firma/posaljiFakturu/'+$scope.invoice.id
		}).then(function successCallback(response){
			if(response.status == 200){
				var modalInstance = $modal.open({
 					templateUrl: 'views/fakturaUspesnoPoslata.html',
					controller: 'AboutCtrl'
					});
			}

		}, function errorCallback(response){
			if(response.status == 400){
				var modalInstance = $modal.open({
      				templateUrl: 'views/fakturaVecPoslata.html',
      				controller: 'AboutCtrl'
    				});
			}else{
				modalInstance = $modal.open({
      				templateUrl: 'views/greska.html',
      				controller: 'AboutCtrl'
    				});
			}

		});
	};
});
