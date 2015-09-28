'use strict';

angular.module('invoiceItem', ['resource.invoiceItem'])

.controller('invoiceItemCtrl', function (InvoiceItem, $route, $location, $scope, $rootScope, $routeParams, $modalInstance, invoiceItem) {
	
	if(invoiceItem){
		$scope.invoiceItem = invoiceItem;
	}
	else{
		$scope.invoiceItem = new InvoiceItem();	
	}

	$scope.ok = function () {
		$modalInstance.close({'invoiceItem':$scope.invoiceItem,
								'action':'save'});


		if($routeParams.invoiceId != 'new'){
			if($scope.invoiceItem.Redni_broj){
				InvoiceItem.update({pib_dob:$rootScope.pib_dob, url_kupca:$rootScope.url_kupca, invoiceId:$routeParams.invoiceId, Redni_broj:$scope.invoiceItem.Redni_broj}
					,invoiceItem
					,function(){
						console.log('stavka updated');
						$route.reload();
					});
			}
			else{
				InvoiceItem.save({pib_dob:$rootScope.pib_dob, url_kupca:$rootScope.url_kupca, invoiceId:$routeParams.invoiceId}
					,$scope.invoiceItem
					,function(){
						console.log('stavka saved');
						$route.reload();
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

		if($routeParams.invoiceId != 'new'){	//nije nova faktura => postoji u bazi
			if(invoiceItem.Redni_broj){			//stavka ima redni broj => postoji u bazi
				InvoiceItem.delete({pib_dob:$rootScope.pib_dob, url_kupca:$rootScope.url_kupca, invoiceId:$routeParams.invoiceId, Redni_broj:$scope.invoiceItem.Redni_broj},
					function(){
						console.log('stavka deleted');
						$route.reload();
					});
			}
		}
	};
});
