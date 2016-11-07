/*
	SHP UI JS
*/

$(function(){
	//Touch Event
	var $touchSelector = 'button, li';
	
	$(document).on('touchstart touchend touchmove touchcancel', $touchSelector, function(e){
		if(e.type == 'touchstart'){
			if($(this)[0].tagName == 'BUTTON' && $(this).attr('disabled')){
				return;
			}
			$(this).addClass('spc-touch');
		} else {
			$(this).removeClass('spc-touch');
		}
	});
});