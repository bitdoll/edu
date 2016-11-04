/**
 * Board.List
 */
var listController = angular.module('phonecatApp', []);

listController.controller('listController', [ '$scope', '$http',
	function($scope, $http) {
	
		//http
		$scope.getPage = function(){
			$http({
	    		// Method
	    		method : "POST",
	    		// Header
	    		headers: {
	    			 'Content-type' : 'application/json'
	    			,'uuid' : 'test_user_token_uuid'
	    		},
	    		// Parameters
	    		data: {  
	    			 "boardType"		: "NOTICE_BOARD"
	    			,"page"				: "1"
	    			,"size" 			: "10"
	    			,"searchType" 		: "TITLE"
	    			,"searchKeyword" 	: ""
	    		},
	    		url	 : "http://spc.pcf.exntu.com/swapi/board/list"
	    	})
	    	// 성공콜백
	    	.success(function(data, status, headers, config){

	    		$scope.list = data.data.content;
	    		console.info('$scope.list', $scope.list);
	    		
			})
			// 에러콜백
			.error(function(data, status, headers, config){
				
				//_alert.error(_MESSAGE.error.replace("{0}", status));
				
			});
	    }
		
		$scope.getPage();
	} 
]);


