
$( document ).ready( function() {
	var $noticeList	= $( '#noticeList' );
	var $template 	= $( '#templateNoticeList' );
	var $btnCreate	= $( '#createBtn' );
	var $btnHome    = $( '#homeBtn');
	
	var $btnMore	= $( '#moreBtn' );
	var nPage		= 1;

	function getList() {
		$.ajax(
			{
				contentType	: 'application/json; charset=utf-8',
				url  		: targetUrl + '/swapi/board/list',
				type 		: 'POST',
				dataType	: 'json',
				beforeSend 	: function( xhr ) {
					xhr.setRequestHeader( 'uuid', localStorage.getItem( 'uuid' ) );
				},
				data 		: JSON.stringify(
					{
						"boardType" 	: "NOTICE_BOARD",
						"page" 			: nPage,
						"size"			: "10",
						"searchType"	: "TITLE",
						"searchKeyword"	: ""
					}
				),
				success		: function( data ) {
					var arrData = data.data.content;

					if( arrData ) {
						for( var idx = 0, nMax = arrData.length; idx < nMax; idx++ ) {
							var objData = arrData[idx];

							var $clone = $template.clone();
							$clone.attr( 'data-key', objData.id );
							$clone.find( '.spc-data-title' ).text( objData.title );
							$clone.find( '.spc-data-date' ).text( objData.modifiedTime );

							$noticeList.append( $clone );
						}
					}
				},
				error		: function() {
					console.info( arguments );
				}
			}
		);
	}	// Function - getList

	$btnMore.bind( 'click', function() {
		nPage++;
		getList();
	});

	$noticeList.on( 'click', 'li', function() {
		var id = $( this ).attr( 'data-key' );
		localStorage.setItem( 'viewId', id );
		window.location.href = 'board_detail.html';
	});

	$btnHome.on( 'click', function(event){
		window.location.href = "main.html";
	});
	
//	$btnCreate.on( 'click', function( event ) {
//		event.preventDefault();
//
//		if (!navigator.camera) {
//		  alert("Camera API not supported", "Error");
//		  return;
//		}
//
//		var options =
//			{   quality: 50,
//				destinationType: Camera.DestinationType.DATA_URL,
//			 	sourceType: 1,      // 0:Photo Library, 1=Camera, 2=Saved Album
//			 	encodingType: 0     // 0=JPG 1=PNG
//		  	};
//
//		navigator.camera.getPicture(
//			function(imgData) {
//				$( '.media-object' ).attr('src', "data:image/jpeg;base64,"+imgData );
//		  	},
//		  	function() {
//				alert('Error taking picture', 'Error');
//		  	},
//		 	options
//		 );
//
//		return false;
//
//	});

	getList();

});

