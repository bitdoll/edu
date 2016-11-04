/**
 *  Basic Resources 
 */

var messages = {
		'notInputGroup' : '그룹명이 입력되지 않았습니다.',
		'notInputTitle' : '제목이 입력되지 않았습니다.',
		'notInputCon' 	: '내용이 입력되지 않았습니다.',
		'successDelete'		: '삭제가 완료되었습니다.',
		'successSave'		: '저장이 완료되었습니다.',
		'errorSave'			: '저장중에 에러가 발생하였습니다.',
		'errorLoad'			: '불러오는중에 에러가 발생하였습니다.'
};

var global = {
	prevPage 		: '',		// 이전 페이지
	db		 		: null,		// 데이터 베이스
	selectedMemo 	: '',		// 선택된 메모 아이디
	delPopupRole	: '',		// 삭제 팝업의 역할 ( memo / group )
	isEdit			: false		
	// 현재 입력페이지의 기능 구분 ( 수정 or 입력 ) -- 평상시에 입력으로 하고, 수정버튼 눌릴때만 값 변경
	// 값이 저장되면서, 기존값으로 변경
	
};