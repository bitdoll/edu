/**
 * 최근글 목록에 관련된 스크립트 모음
 */

$( document ).ready( function() {
	// 최근글 목록 - 메모 입력 버튼 이벤트 설정
	$( "#pRML_inputMemo" ).tap( function() {
		// DB 처리 완료 후의 작업	    		
		$.mobile.changePage("#pageInputMemo");
	});
	// 최근글 목록 - 목록 편집 버튼 이벤트 설정
	$( "#pRML_editList" ).tap( function() {
		var $list = $('#pRecentMemoList_memoList');
		
		var $this = $( this );
		if( 'true' == $this.attr( 'data-toEdit' ) ) {
			// 편집형으로 변경
			
			// 속성 설정		
			$list.attr( 'data-split-icon', 'plus' );
			$list.attr( 'data-split-theme', 'a' );
			
			// 내용 설정
			funcPageRecentMemo.loadList( function() {			
				// 새로고침		
				$list.listview( 'refresh' );
				$list = null;
			}, true );
			
			
			$this.text( "취소" );
			$this.attr( "data-toEdit", 'false' );
		}
		else {
			// 일반 리스트로 변경
			
			$list.removeAttr( 'data-split-icon' );
			$list.removeAttr( 'data-split-theme' );
			
			// 내용 설정
			funcPageRecentMemo.loadList( function() {			
				// 새로고침		
				$list.listview( 'refresh' );
				$list = null;
			});
			
			
			$this.text( "편집" );
			$this.attr( "data-toEdit", 'true' );
			
		}
		
		$this = null;
		
	});
});

var funcPageRecentMemo = {
	// 최근글 목록 불러오기
	loadList : function ( fn, isEdit ) {
		
		var objRecentList = $("#pRecentMemoList_memoList");
		
		// "http://localhost:8080/api/memos"
		$.ajax( {
			  type	: "get",
			  url	: "/api/memo",
			  dataType:"json",
			  contentType:"application/json; charset=UTF-8",			  
			  data 	: {},
			  success: function(data){			
				if( data._embedded.memo ) {
					
					var rows = data._embedded.memo;
					
					objRecentList.empty();

					// SQL 실행 성공 시의 처리
					if( 1 <= rows.length ) {
						// 결과 행 세트 루프 처리
						
						// event unbind
						$("li", objRecentList).unbind( "tap" );

						var row;
						for ( var idx = 0, nMax = rows.length; idx < nMax; idx++) {
							
							// 인덱스를 지정하여 행 객체를 얻음
							row = rows[idx];
							
							if( isEdit ) {										
								objRecentList.append( '<li><a href=\"#\">'
										+ row.title
										+ '<a data-memoId="' + row.id + '" data-role="button" data-icon="minus" data-rel="dialog" data-transition="slidedown" >dialog</a>'
										+ '</a></li>' );
							}
							else {
								objRecentList.append( '<li data-memoId="' + row.id + '" ><a href="#">' + row.title + '</a></li>' );										
							}
						}								
						
					} // rs is not empty
									
					// DB 처리 완료 후의 작업
					
					if( !isEdit ) {					
						// event bind
						$("li", objRecentList).bind( "tap", function() {
							var $this = $( this );
							funcPageViewMemo.setContent( 
								$this.attr( "data-memoId"), 
								function() {
									$.mobile.changePage("#pageViewMemo");
								});
							$this = null;
						});												
					}
					else {
						$("a[data-icon=minus]", objRecentList).bind( "tap", function() {
							var $this = $( this );
							funcPageDeletePopup.showDialog( $this.attr( "data-memoId" ), 'memo' );
									
							$this = null;
									
						});																	
					}
					if( 'function' == typeof fn ) fn();
				}	
				else {
					// DB 처리 오류 시의 실행 내용
					alert(messages.errorLoad + err.code);
				}
			  }, 
			  error: function(data, status, err) {	    				 
					// DB 처리 오류 시의 실행 내용
					alert(messages.errorLoad + err.code);
			  } 			  
			  
		});	
		
	}		
};

