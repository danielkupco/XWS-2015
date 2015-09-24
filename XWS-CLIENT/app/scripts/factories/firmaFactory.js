angular.module('resource.firma', ['ngResource'])
	.factory('Firma', function ($resource) {
		return $resource('http://localhost:8080/xws/api/firma/firme/:firmaId',null, {
        'update': { method:'PUT' }
    });
})