'use strict';

/* controllers */

var phonecatControllers = angular.module('phonecatControllers', []);

phonecatControllers.controller('PhoneListCtrl', [ '$scope', '$http',
    function($scope, $http) {
		$http.get('/app/angularjs/data/phones9.json').success(function(data) {
			$scope.phones = data;
		});
		$scope.orderProp = 'age';	
		
		$scope.dropdown = {
			selectedText : 'Newest',
			isopen : false,
			toggleDropdown : function( order, text, $event) {
		          
	        	$event.preventDefault();
	        	$event.stopPropagation();
	        	
	        	$scope.dropdown.isopen = !$scope.dropdown.isopen;
	        	$scope.dropdown.selectedText = text;
	          
	        	$scope.orderProp = order;
	        }			
		};
		
	} 
]);

phonecatControllers.controller('PhoneDetailCtrl', ['$scope', '$routeParams', '$http',
    function($scope, $routeParams, $http) {
		$http.get( '/app/angularjs/data/' + $routeParams.phoneId + '.json').success(
			function(data) {
				$scope.phone = data;
			}
		);
	}
]);