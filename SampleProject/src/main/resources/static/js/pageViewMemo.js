/**
 * 메모 상세 보기에 관련된 스크립트 모음
 */

$( document ).ready( function() {
	
	// 메모 상세보기에 대한 수정 버튼 	
	$( '#btnToEditPage' ).tap( function() {
		global.isEdit = true;	// set Edit Mode
		global.selectedMemo = $( this ).attr( 'data-memoId' );
		
		$.ajax( {
			  type		 : "get",
			  url		 : "/api/memo/" + global.selectedMemo,
			  dataType	 :"json",
			  contentType:"application/json; charset=UTF-8",
			  success: function(data){					
				  // DB 처리 완료 후의 작업
				  $.mobile.changePage( "#pageInputMemo" );
			  }, 
			  error: function(data, status, err) {	    				 
				  // DB 처리 오류 시의 실행 내용
				  alert( messages.errorLoad + err.code );
			  } 	
		});
		
	});
});

var funcPageViewMemo = {
	setContent : function( memoId, fn ) {
		
		$.ajax( {
			  type		 : "get",
			  url		 : "/api/memo/" + memoId,
			  dataType	 :"json",
			  contentType:"application/json; charset=UTF-8",
			  success: function(data) {
				
				// SQL 실행 성공 시의 처리
				if( data ) {
					// 결과 행 세트 루프 처리
					$( "#btnToEditPage" ).attr( "data-memoId", memoId );
					$( "#PVM_title" ).text( data.title );
					$( "#PVM_content" ).text( data.content );		
				} // rs is not empty
				
				// DB 처리 완료 후의 작업
				if( 'function' == typeof fn ) fn();				
				
			  }, 
			  error: function(data, status, err) {	    				 
				  // DB 처리 오류 시의 실행 내용
				  alert( messages.errorLoad + err.code );
			  } 	
		});		
		
	}
}