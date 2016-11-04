'use strict';

/* controllers */

var phonecatControllers = angular.module('phonecatControllers', []);

phonecatControllers.controller('PhoneListCtrl', [ '$scope', '$http',
    function($scope, $http) {
		$http.get('/app/angularjs/data/phones7.json').success(function(data) {
			$scope.phones = data;
		});
		$scope.orderProp = 'age';
	} 
]);

phonecatControllers.controller('PhoneDetailCtrl', [ '$scope', '$routeParams',
    function($scope, $routeParams) {
		$scope.phoneId = $routeParams.phoneId;
	} 
]);