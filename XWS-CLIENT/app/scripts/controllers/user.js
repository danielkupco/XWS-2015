'use strict';

angular.module('user', ['resource.user'])

.controller('userCtrl', function ($scope,$rootScope, User, $log, $location) {
	$scope.login = function () {
		var promise = User.login($scope.user);
		$scope.user = {};
		promise.then(function (data) {
			$log.info(data);
			$rootScope.user = data;
			$location.path('invoice-list');
		});
	}
});
