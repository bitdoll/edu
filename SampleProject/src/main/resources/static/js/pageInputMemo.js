/**
 * 메모입력화면에 관련된 스크립트 모음
 */
$( document ).ready( function() {

	// 메모 입력 화면 - 메모 저장 버튼 이벤트 설정
	$( "#pInputMemo_saveMemo" ).tap( function() {
		
		var strTitle = $( "#pInputMemo_inputTitle" ).val();	//	메모 타이틀
		var strCon	 = $( "#pInputMemo_inputContent" ).val();	//	메모 내용
		
		// 값 입력 유효성 체크
		if( '' == strTitle || 0 == strTitle.length ) {
			alert( messages.notInputTitle );
			return;
		}
		if( '' == strCon || 0 == strCon.length ) {
			alert( messages.notInputCon );
			return;
		}
		
		if( global.isEdit ) {	
			$.ajax( {
				type	: "PUT",
				url		 : "/api/memo/" + global.selectedMemo,
				dataType:"json",
		        contentType:"application/json; charset=UTF-8",
				data 	: JSON.stringify({
					'title' 	: strTitle,
					'content'	: strCon
				}),
				success: function(data){	
					// 정상적인 처리시 실행함수
					alert( messages.successSave );
					
					global.isEdit = false;	// 초기화
					
			    	// DB 처리 완료 후의 작업	    		
			    	location.href = "/";
				}, 
				error: function(data, status, err) {	    				 
					// DB 처리 오류 시의 실행 내용
					alert( messages.errorSave + err.code );
				}
			});
		}
		else {

			$.ajax( {
				type		: "POST",
				url			: "/api/memo",
				dataType	: "json",
				contentType	: "application/json; charset=UTF-8",
				data : JSON.stringify({
					'title'  : strTitle,
					'content': strCon
				}),
				success: function(data) { 	
					
					// 정상적인 처리시 실행함수
					alert( messages.successSave );
					
					global.isEdit = false;	// 초기화
					
			    	// DB 처리 완료 후의 작업	    		
			    	location.href = "/";

				}, 
				error: function(data, status, err) {	    				 
					// DB 처리 오류 시의 실행 내용
					alert( messages.errorSave + err.code );
				}
			});
		}
		
	});
	
});