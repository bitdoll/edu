/**
 * 설정 관련된 스크립트 모음
 */


var funcPageSetting = {
	changeType1 : function() {
			$('head link[href*="jquery"]' ).remove();		
			$('head').prepend( '<link rel=\'stylesheet\' href=\'resource/css/jquery.mobile-type1.css\' />' );		
			localStorage.theme = 'type1';
	},
	changeType2 : function() {
		$('head link[href*="jquery"]' ).remove();		
		$('head').prepend( '<link rel=\'stylesheet\' href=\'resource/css/jquery.mobile-type2.css\' />' );
		localStorage.theme = 'type2';
	}	
};