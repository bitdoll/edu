'use strict';

/* controllers */

var phonecatApp = angular.module('phonecatApp', []);

phonecatApp.controller(
	'PhoneListCtrl', 
	[ 
	  	'$scope', 
	  	'$http',
		function($scope, $http) {
			$http.get('/app/angularjs/data/phones6.json').success(function(data) {
				$scope.phones = data;
			});

			$scope.orderProp = 'age';
		} 
	]
);