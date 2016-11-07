
$( document ).ready( function() {

	var $inputEmail = $( '#email' );
	var $inputPw	= $( '#password' );
	var $btnLogin	= $( '#loginBtn' );

	$btnLogin.bind( 'click', function() {

		$.ajax(
			{
				contentType	: 'application/json; charset=utf-8',
				url  		: targetUrl + '/swapi/user/login',
				type 		: 'POST',
				dataType	: 'json',
				data 		: JSON.stringify({
					email 		: $inputEmail.val(),
					password	: $inputPw.val()
				}),
				success		: function( data ) {
					console.info( data );
					if( data.resultCode == "000"){
						localStorage.setItem( "uuid", data.uuid );
						window.location.href = "main.html";
					}else{
						alert(data.resultMessage);
					}
				},
				error		: function() {
					console.info( arguments );
				}
			}
		);
	});

});
