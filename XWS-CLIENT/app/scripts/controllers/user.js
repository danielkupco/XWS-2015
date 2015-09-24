'use strict';

angular.module('user', ['resource.user'])

.controller('userCtrl', function ($scope, $rootScope, User, $log, $location) {
	$scope.login = function () {
		var promise = User.login($scope.user);
		$rootScope.user = {};
		promise.then(function(data) {
			$log.info(data);
			$rootScope.user = data;
			$location.path('invoice-list');
		});
	};
	/*
	$scope.logout = function () {
		var promise = User.logout();
		promise.then(function(data) {
			$rootScope.user = {};
			$location.path('login');
		});
	};
	*/
});
