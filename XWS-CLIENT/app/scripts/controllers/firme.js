'use strict';

angular.module('firme', ['resource.firma',
	'angular-md5'])

.controller('firmeCtrl', function (Firma, $scope, $rootScope, $location, md5, $log, $route) {

	//preuzimanje niza firmi sa servera
	Firma.query().$promise.then(function (data) {
		$scope.firme = data;

	// da bude selektovana prva stavka u cmb za firmu dobavljaca
	if($rootScope.temp_firma) {
		var keepGoing = true;
		angular.forEach($scope.firme, function(value, index) {
			if($rootScope.temp_firma.id == value.id && keepGoing) {
				$scope.firma = value;
				keepGoing = false;
			}
		});
	}
	else {
		$scope.firma = $scope.firme[0];
		//$rootScope.pib_dob = $scope.firma.PIB;
	}

	// zbog buga kod comboboxa prilikom osvezavanja stranice invoice-list
	if($rootScope.temp_kupac) {
		var keepGoing = true;
		angular.forEach($scope.firme, function(value, index) {
			if($rootScope.temp_kupac.id == value.id && keepGoing) {
				$scope.kupac = value;
				keepGoing = false;
			}
		});
	}
	else {
		$scope.kupac = $scope.firme[0];
		//$rootScope.url_kupca = $scope.kupac.Url;
	}

	}, function (error) {
		console.log(error);
	});

	//$scope.invoices = Invoice.query();

	$scope.firmaSelected = function (firma) {
		$rootScope.pib_dob = firma.PIB;
		console.log('selektovana firma je ' + firma.Naziv_firme + ', pib: ' + firma.PIB);
		$rootScope.temp_firma = firma;
	};

	$scope.kupacSelected = function (kupac) {
		$rootScope.url_kupca = kupac.Url;
		console.log('selektovani kupac je ' + kupac.Url);
		$rootScope.temp_kupac = kupac;
		$route.reload();
	};

});
