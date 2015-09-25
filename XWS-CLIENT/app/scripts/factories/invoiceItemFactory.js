angular.module('resource.invoiceItem', ['ngResource'])
    .factory('InvoiceItem', function ($resource) {
    return $resource('http://localhost:8080/xws/api/firma/:url_kupca/partneri/:pib_dob/fakture/:invoiceId/stavke/:Redni_broj',null, {
        'update': { method:'PUT',
                    params: {url_kupca: '@url_kupca', invoiceId: '@invoiceId', Redni_broj:'@Redni_broj'}
        },
        'save' : { method : 'POST'},
        'query': {
                method: 'GET',
                isArray: true,
                params: { url_kupca: '@url_kupca', pib_dob: '@pib_dob' } // parametri se prosledjuju prilikom poziva query() metode
        }
    });
})