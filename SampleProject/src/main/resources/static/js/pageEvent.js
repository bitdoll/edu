/**
 * 페이지 이벤트 관련 스크립트
 * 
 */

var objScroll = {};

function setScroll( objPage ) {
	
	
	// 스크롤러 생성 - start
	var strPageId = objPage.attr( 'id' );
	
	if( 'undefined' == typeof objScroll[ strPageId ] || null == objScroll[ strPageId ] ) {
		
		var $objSection = $( 'div[data-role=content]', objPage );
		
		$objSection.attr({
			'id' : 'wrap_' + strPageId, 
			'data-scroll-wrapper' : 'true'			
		});
		
		$objSection.children().wrapAll( 
				$( "<div />", {
					'data-scroll-scroller' : 'true'			
				}
				) 
		);
		
		
		objScroll[ strPageId ] = new iScroll( 'wrap_' + strPageId );
		
		$objSection = null;
	}
	else {
		objScroll[ objPage.attr( 'id' ) ].refresh();
	}
	
	strPageId = null;
	
	// 스크롤 생성 - end
}

document.addEventListener('touchmove', function (e) { e.preventDefault(); }, false);
document.addEventListener('DOMContentLoaded', function() {
	setScroll( $( "div[data-role=page]:first-child" ) ); 
}, false);

$(document).ready(function() {

	// 테마 적용
	if( 'undefined' == typeof localStorage.theme ) {
		$('head').prepend( '<link rel=\'stylesheet\' href=\'resource/css/jquery.mobile-type1.css\' />' );			
	}
	else {
		$('head').prepend( '<link rel=\'stylesheet\' href=\'resource/css/jquery.mobile-' + localStorage.theme + '.css\' />' );	
	}
	
	/*
	 * depth 1
	 * 		pageRecentMemoList 	-- 최근글 목록
	 * 		pageSetting			-- 설정화면
	 * 
	 * input page
	 * 		pageInputMemo		-- 메모입력화면
	 */
	funcInitPageRecentMemoList();		
	$('div[data-role=page]').live('pageshow', function(event, ui) {
		global.prevPage = ui.prevPage;
		
		// 스크롤바 새로고침
		setScroll( $( event.target ) );
		
		// page active event handler
		if( 'pageRecentMemoList' == event.target.id ) {
			funcInitPageRecentMemoList();
		}
		else if ('pageInputMemo' == event.target.id) {
			funcInitPageInputMemo();
			
			return;
		}
	});

});

//뒤로가기 메서드
function funcGoBack() {
	$.mobile.changePage( global.prevPage, 'slide', true, true );	
}

// 최근글 목록 페이지 활성화 메서드
function funcInitPageRecentMemoList() {
	
	var objMemoList = $('#pRecentMemoList_memoList');
	if (0 == objMemoList.children().length) {
		funcPageRecentMemo.loadList( function() {					
			// DB 처리 완료 후의 작업
			objMemoList.listview('refresh');				
		});
	} 
	else {
		objMemoList.listview('refresh');
	}	
}

// 메모 입력 화면 활성화 메서드
function funcInitPageInputMemo() {
	// 입력 항목 초기화
	if( global.isEdit ) {
		// 내용 삽입					
		$( "#pInputMemo_inputTitle" ).val( $( "#PVM_title" ).text() );	
		$( "#pInputMemo_inputContent" ).val( $( "#PVM_content" ).text() );				
	}
	else {		
		$("#pInputMemo_inputTitle").val('');
		$("#pInputMemo_inputContent").val('');
	}
}
