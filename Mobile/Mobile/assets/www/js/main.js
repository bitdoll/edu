
$( document ).ready(function() {

	var $btnLogin	= $( '#noticeBtn' );

	$btnLogin.bind( 'click', function() {
		window.location.href = "board_list.html";
	});

});

document.addEventListener('deviceready', function() {

	document.addEventListener("backbutton", function(e){
	   e.preventDefault();
	   navigator.app.exitApp();
	}, false);

}, false);