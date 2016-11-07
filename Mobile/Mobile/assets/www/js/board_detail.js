$( document ).ready( function() {
	var $btnBack	= $( '#backBtn' );
	var $spanTitle 	= $( '#title' );
	var $spanDate	= $( '#date' );
	var $contents	= $( '#contents' );

	$.ajax(
		{
			contentType	: 'application/json; charset=utf-8',
			url  		: 'http://jpa.crepaas.org/swapi/board/detail',
			type 		: 'POST',
			dataType	: 'json',
			beforeSend 	: function( xhr ) {
				xhr.setRequestHeader( 'uuid', localStorage.getItem( 'uuid' ) );
			},
			data 		: JSON.stringify(
				{
					'id' : localStorage.getItem( 'viewId' )
				}
			),
			success		: function( data ) {
				if( data && data.data  ) {
					var objData = data.data;
					$spanTitle.text( objData.title );
					$spanDate.text( objData.createdTime );
					$contents.text( objData.contents );
				}
			},
			error		: function() {
				console.info( arguments );
			}
		}
	);

	$btnBack.bind( 'click', function() {
		window.history.back();
	});
});