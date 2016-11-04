/**
 * 삭제 관련 팝업 설정 스크립트
 * 
 */

var funcPageDeletePopup = {
	// 팝업을 표시함
	showDialog : function ( groupId, memoId, flag ) {
		
		// 선택값 저장
		global.selectedGroup = groupId;
		global.selectedMemo = memoId;
		global.delPopupRole = flag;
		
		// show dialog
		$.mobile.changePage( "#pageDeletePopup", "slidedown" );
			
	},
	// 확인버튼에 대한 이벤트	
	confirm : function() {
		
		var $this = this;
		
		if( 'memo' == global.delPopupRole ) {
			// 메모 정보 저장
			global.db.transaction(   
					function( tx ) {    
						tx.executeSql(
								' DELETE FROM TB_MEMO WHERE memo_id = ? ', 
								[ Number( global.selectedMemo ) ], 
								function( tx, rs ) {
									// 정상적인 처리시 실행함수
									alert( messages.successDelete );
								}
						);			
					},
					function(err) {
						// DB 처리 오류 시의 실행 내용
						console.error( err );
						alert( messages.errorSave + err.code );
					},
					function() {
						// DB 처리 완료 후의 작업	    		
						$this.hide();
					}
			);							
		}
		
		if( 'group' == global.delPopupRole ) {
			global.db.transaction(   
					function( tx ) {    
						tx.executeSql(
								' DELETE FROM TB_MEMO WHERE group_id = ? ', 
								[ Number( global.selectedGroup ) ], 
								function( tx, rs ) {
									// 정상적인 처리시 실행함수
								}
						);			
						tx.executeSql(
								' DELETE FROM TB_GROUP WHERE group_id = ? ', 
								[ Number( global.selectedGroup ) ], 
								function( tx, rs ) {
									// 정상적인 처리시 실행함수
									
								}
						);			
					},
					function(err) {
						// DB 처리 오류 시의 실행 내용
						console.error( err );
						alert( messages.errorSave + err.code );
					},
					function() {
						alert( messages.successDelete );
						// DB 처리 완료 후의 작업	    		
						$this.hide();
					}
			);				
		}
		
	},
	// 팝업 숨김
	hide : function() {
		$('.ui-dialog').dialog('close');
	}
};

