'use strict';

angular.module('invoiceItem', ['resource.invoiceItem'])

.controller('invoiceItemCtrl', function (InvoiceItem, $scope, $rootScope, $modalInstance, invoiceItem) {
	if(invoiceItem){
		$scope.invoiceItem = invoiceItem;
	}
	else{
		$scope.invoiceItem = {};	
	}

	$scope.ok = function () {
		$modalInstance.close({'invoiceItem':$scope.invoiceItem,
								'action':'save'});
	};

	$scope.cancel = function () {
		$modalInstance.dismiss('cancel');
	};

	$scope.delete = function () {
		$modalInstance.close({'invoiceItem':$scope.invoiceItem,
								'action':'delete'});
	};
});
