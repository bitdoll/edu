package com.spc.jpa.common;

public class Constant {

	public class Code
    {
		// Common Code
        public static final String CODE_SUCCESS 		= "000";					// 성공
        public static final int CODE_RESPONSE_SUCESS 	= 200;						// 성공
        public static final String CODE_SERVER_ERR 		= "900";					// 실패
        
        // Login Code
        public static final String CODE_LOGIN_FAIL 		= "101";					// 로그인 실패
        public static final String CODE_DUPLICATION 	= "102";					// 회원가입 - 계정 중복
        public static final String CODE_LOGIN_NON_VALID = "103";					// 유효하지 않은 로그은 -> 재로그인 요청
        
        // Board Code
        public static final String CODE_BOARD_NON_VALID = "201";					// 존재하지 않는 게시물
    }
	
	public class Message
    {
		// Common MSG
		public static final String MSG_SUCCESS 			= "Success";				// 성공
        public static final String MSG_SERVER_ERR 		= "Fail";					// 실패
        
        // Login MSG
        public static final String MSG_LOGIN_FAIL 		= "LOGIN FAIL";				// 로그인 실패
        public static final String MSG_DUPLICATION 		= "DUPLICATION ACCOUNT";	// 회원가입 - 계정 중복
        public static final String MSG_LOGIN_NON_VALID 	= "NON VALID LOGIN";		// 유효하지 않은 로그은 -> 재로그인 요청
        
        // Board MSG
        public static final String MSG_BOARD_NON_VALID 	= "NON EXIST BOARD";		// 존재하지 않는 게시물
    }

}
