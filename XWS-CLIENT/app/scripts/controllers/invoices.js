'use strict';

 angular.module('invoices', ['resource.invoice',
 	'angular-md5'])

 .controller('invoicesListCtrl', function (Invoice, $scope, $location, md5, $log, $rootScope) {

 	//preuzimanje niza faktura sa servera
 	Invoice.query({url_kupca: $rootScope.url_kupca, pib_dob: $rootScope.pib_dob}).$promise.then(function (data) {
 		$scope.invoices = data;
 	}, function (error) {
 		console.log(error);
 	});

	//$scope.invoices = Invoice.query();
	//$log.info($scope.invoices.length);//0
	//kada smo kliknuli na red u tabeli prelazimo na stranicu za editovanje fakture sa zadatim id-om
 	$scope.insertOrEditInvoice = function (invoice) {
 		if(invoice){
 			$location.path('/invoice/'+invoice.id);
 		}
 		else{
			$location.path('/invoice/new');
 		}
 	}

	//funkcija koja otvara datepicker
	$scope.openDatepicker = function($event, opened) {
		$event.preventDefault();
		$event.stopPropagation();
		$scope[opened] = true;
	};

	// invoice order by
	$scope.invoicePredicate = 'Zaglavlje.Dobavljac.Naziv';
	$scope.invoiceReverse = true;
	$scope.invoiceOrder = function(predicate) {
		$scope.invoiceReverse = ($scope.invoicePredicate == predicate) ? !$scope.invoiceReverse : false;
		$scope.invoicePredicate = predicate;
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

	$scope.beforeComparator = function (actual, expected) {
		if(actual == null || expected == null) {
			return true;
		}

		var d = new Date(actual);
		return  d < expected;
	};

	$scope.afterComparator = function (actual, expected) {
		if(expected == null) {
			return true;
		}

		var d = new Date(actual);
		return d > expected;
	};

 });
