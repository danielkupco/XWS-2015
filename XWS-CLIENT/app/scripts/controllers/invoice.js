'use strict';

angular.module('invoice', [
	'resource.invoice',
	'ui.bootstrap',
	'invoiceItem',
	'resource.invoiceItem'])

.controller('invoiceCtrl', function (Invoice, $scope, $routeParams, $rootScope, $modal, $log, $location, InvoiceItem, $route) {

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
			if(!invoiceItem.id && data.action==='save'){
				$scope.invoice.Stavka.push(invoiceItem);				
			}
			//ako stavka treba da se obrise izbaci se iz niza
			if(data.action==='delete'){
				var index = $scope.invoice.Stavka.indexOf(invoiceItem);
				$scope.invoice.Stavka.splice(index, 1);
				//ako je stavka imala i id, treba da se obrise i na serveru (da li je to dobro?)
				if(invoiceItem.id){
					InvoiceItem.delete({url_kupca:$rootScope.url_kupca, pib_dob:$rootScope.pib_dob, invoiceItemId:invoiceItem.id});
				}
			}
		}, function () {
			$log.info('Modal dismissed at: ' + new Date());
		});
	};

	//cuvanje izmena
	$scope.save = function () {
		if($scope.invoice.id){
			//zbog cega redirekcija ide na callback?
			angular.forEach($scope.invoice.Stavka, function(value, key) {
  				console.log(key + ': ' + value);
  				console.log(value);

  				//var temp = angular.toJson(value);

  				if(value.Redni_broj){
  					InvoiceItem.update({url_kupca:$rootScope.url_kupca, pib_dob:$rootScope.pib_dob, invoiceId:$scope.invoice.id, Redni_broj:value.Redni_broj}, value, function(){
  					});
  				}
  				else{
  					InvoiceItem.save({invoiceId:$scope.invoice.id}, value, function(){
  					});
  				}
			});

			$location.path('/invoice/'+$scope.invoice.id);

			/*$scope.invoice.$update({invoiceId:$scope.invoice.id},function () {
				$location.path('/invoiceList');
			});*/
		}
		else{
			$scope.invoice.$save(function () {
				$location.path('/invoiceList');
			});
		}
		$log.info("save");
	}

	$scope.delete = function () {
		if($scope.invoice.id){
			$scope.invoice.$delete({url_kupca:$rootScope.url_kupca, pib_dob:$rootScope.pib_dob, invoiceId:$scope.invoice.id}, function () {
				$location.path('invoiceList');
			});
		}
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
		else return true;
	};

});
