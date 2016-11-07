
$( document ).ready( function() {

	var $inputEmail = $( '#email' );
	var $inputPw	= $( '#password' );
	var $btnLogin	= $( '#loginBtn' );

	$btnLogin.bind( 'click', function() {

		$.ajax(
			{
				contentType	: 'application/json; charset=utf-8',
				url  		: 'http://jpa.crepaas.org/swapi/user/login',
				type 		: 'POST',
				dataType	: 'json',
				data 		: JSON.stringify({
					email 		: $inputEmail.val(),
					password	: $inputPw.val()
				}),
				success		: function( data ) {
					localStorage.setItem( "uuid", data.uuid );
					/// window.location.replace( "main.html" );
					window.location.href = "main.html";
				},
				error		: function() {
					console.info( arguments );
				}
			}
		);
	});

});

document.addEventListener('deviceready', function() {

	document.addEventListener( "backbutton", function(e){
	   e.preventDefault();
	   navigator.app.exitApp();
	}, false);

}, false);