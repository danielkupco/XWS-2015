'use strict';

angular.module('invoiceItem', ['resource.invoiceItem'])

.controller('invoiceItemCtrl', function (InvoiceItem, $location, $scope, $rootScope, $routeParams, $modalInstance, invoiceItem) {
	if(invoiceItem){
		$scope.invoiceItem = invoiceItem;
	}
	else{
		$scope.invoiceItem = {};	
	}

	$scope.ok = function () {
		$modalInstance.close({'invoiceItem':$scope.invoiceItem,
								'action':'save'});


		if($routeParams.invoiceId != 'new'){
			if(invoiceItem){
				InvoiceItem.update({pib_dob:$rootScope.pib_dob, url_kupca:$rootScope.url_kupca, invoiceId:$routeParams.invoiceId, Redni_broj:$scope.invoiceItem.Redni_broj}
					,invoiceItem
					,function(){
						$location.path('/invoice/'+$routeParams.invoiceId);
					});
			}
		}

	};

	$scope.cancel = function () {
		$modalInstance.dismiss('cancel');
	};

	$scope.delete = function () {
		$modalInstance.close({'invoiceItem':$scope.invoiceItem,
								'action':'delete'});

		if($routeParams.invoiceId != 'new'){
			if(invoiceItem){
				InvoiceItem.delete({'pib_dob':$rootScope.pib_dob, 'url_kupca':$rootScope.url_kupca, 'invoiceId':$routeParams.invoiceId, 'Redni_broj':$scope.invoiceItem.Redni_broj}, 
					function(){
						$location.path('/invoice/'+$routeParams.invoiceId);
					});
			}
		}
	};
});
