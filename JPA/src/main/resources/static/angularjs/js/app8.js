'use strict';

/* App Module */

var phonecatApp = angular.module('phonecatApp', [ 
	'ngRoute',
	'phonecatControllers' 
]);

phonecatApp.config([ '$routeProvider', 
    function($routeProvider) {
		$routeProvider
			.when('/phones', {
				templateUrl : 'views/phone-list8.html',
				controller : 'PhoneListCtrl'
			})
			.when('/phones/:phoneId', {
				templateUrl : 'views/phone-detail8.html',
				controller : 'PhoneDetailCtrl'
			})
			.otherwise({
				redirectTo : '/phones'
			});
} ]);