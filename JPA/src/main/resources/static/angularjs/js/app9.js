'use strict';

/* App Module */

var phonecatApp = angular.module('phonecatApp', [ 
	'ui.bootstrap',
	'ngRoute',
	'phonecatControllers'
]);

phonecatApp.config([ '$routeProvider', 
    function($routeProvider) {
		$routeProvider
			.when('/phones', {
				templateUrl : 'views/phone-list9.html',
				controller : 'PhoneListCtrl'
			})
			.when('/phones/:phoneId', {
				templateUrl : 'views/phone-detail9.html',
				controller : 'PhoneDetailCtrl'
			})
			.otherwise({
				redirectTo : '/phones'
			});
} ]);