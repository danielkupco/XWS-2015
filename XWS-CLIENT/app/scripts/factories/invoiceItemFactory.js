angular.module('resource.invoiceItem', ['ngResource'])
	.factory('InvoiceItem', function ($resource) {
	return $resource('http://localhost:8080/xws/api/firma/firma1/partneri/44444555556/fakture/:invoiceId/stavke/:Redni_broj',null, {
        'update': {	method:'PUT',
        			params: {invoiceId: '@invoiceId', Redni_broj:'@Redni_broj'}
        			},
        'save' : { method : 'POST'}
    });
})