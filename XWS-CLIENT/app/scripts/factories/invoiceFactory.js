angular.module('resource.invoice', ['ngResource'])
	.factory('Invoice', function ($resource, $rootScope) {

		return $resource('http://localhost:8080/xws/api/firma/:url_kupca/partneri/:pib_dob/fakture/:invoiceId', {}, {
	        'update': { method:'PUT' }, // redefinisanje metode
	        'query': {
	        	method: 'GET',
	        	isArray: true,
	        	params: { url_kupca: '@url_kupca', pib_dob: '@pib_dob' } // parametri se prosledjuju prilikom poziva query() metode
        	}
        });
})