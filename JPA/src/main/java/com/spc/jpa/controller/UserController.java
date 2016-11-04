package com.spc.jpa.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spc.jpa.common.Constant.Code;
import com.spc.jpa.common.Constant.Message;
import com.spc.jpa.common.MessageUtil;
import com.spc.jpa.common.Path;
import com.spc.jpa.common.Utils;
import com.spc.jpa.domain.user.User;
import com.spc.jpa.domain.user.UserRepository;
import com.spc.jpa.vo.UserToken;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@RestController
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	MessageUtil messageUtil = new MessageUtil();
	
	Utils utils = new Utils();
	
	/**
	 * 로그인
	 */
    @ApiOperation(value = "users", notes = "post : login", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(code = Code.CODE_RESPONSE_SUCESS, message = Message.MSG_SUCCESS)})
	@RequestMapping(path = Path.USER_LOGIN, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> login(HttpServletRequest request,	HttpServletResponse response,
			@ApiParam(name = "user data", defaultValue="{\"email\" : \"spc@gmail.com\", \"password\":\"spc\"}", value ="user data insert", required = true)@RequestBody User user){
    	Map<String, Object> resultMap = new HashMap<>();
    	try {
    		User result = userRepository.findByEmailAndPassword(user.getEmail(), utils.encodeSHA256(user.getPassword()));
    		if ( result != null ) {
    			// login user token save 
    			UserToken token = new UserToken(result.getName(), result.getEmail());
    			
    			result.setTokenUuid(token.getKey());
    			userRepository.save(result);
    			
				resultMap.put("resultCode", Code.CODE_SUCCESS);
				resultMap.put("resultMessage", Message.MSG_SUCCESS);
				resultMap.put("uuid", token.getKey());
				
    		} else {
    			resultMap.put("resultCode", Code.CODE_LOGIN_FAIL);
				resultMap.put("resultMessage", Message.MSG_LOGIN_FAIL);
    		}
		} catch (Exception e) {
			resultMap.put("resultCode", Code.CODE_SERVER_ERR);
			resultMap.put("resultMessage", Message.MSG_SERVER_ERR);
		}
    	return resultMap;
	}
    
    /**
	 * 중복 아이디 체크
	 */
    @ApiOperation(value = "users", notes = "post : countById", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(code = Code.CODE_RESPONSE_SUCESS, message = Message.MSG_SUCCESS)})
	@RequestMapping(path = Path.USER_ID_CHECK, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> countById(@ApiParam(name = "E-mail Address", defaultValue="{\"email\" : \"spc@gmail.com\"}", value ="ID validation check", required = true)@RequestBody User user){
    	Map<String, Object> resultMap = new HashMap<>();
    	try {
    		long result = userRepository.countByEmail(user.getEmail());
    		if ( result == 0 ) {
    			resultMap.put("resultCode", Code.CODE_SUCCESS);
				resultMap.put("resultMessage", Message.MSG_SUCCESS);
    		} else {
    			resultMap.put("resultCode", Code.CODE_DUPLICATION);
				resultMap.put("resultMessage", Message.MSG_DUPLICATION);
    		}
		} catch (Exception e) {
			resultMap.put("resultCode", Code.CODE_SERVER_ERR);
			resultMap.put("resultMessage", Message.MSG_SERVER_ERR);
		}
    	return resultMap;
	}
    
    /**
     * 회원가입
     */
    @ApiOperation(value = "users", notes = "post : register", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(code = Code.CODE_RESPONSE_SUCESS, message = Message.MSG_SUCCESS)})
	@RequestMapping(path = Path.USER_REGISTER, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> registerUser(@ApiParam(name = "userVO", defaultValue="{\"email\":\"id@mail.com\",\"password\":\"password\",\"name\":\"홍길동\"}", value ="user data insert", required = true)@RequestBody User user){
    	Map<String, Object> resultMap = new HashMap<>();
    	try {
    		User userVO = new User();
    		userVO.setName(user.getName());
    		userVO.setPassword(utils.encodeSHA256(user.getPassword()));
    		userVO.setEmail(user.getEmail());
    		User result = userRepository.save(userVO);
    		
    		if ( result != null ){
    			resultMap.put("resultCode", Code.CODE_SUCCESS);
				resultMap.put("resultMessage", Message.MSG_SUCCESS);
				resultMap.put("data", result);
    		}
		} catch (Exception e) {
			resultMap.put("resultCode", Code.CODE_SERVER_ERR);
			resultMap.put("resultMessage", Message.MSG_SERVER_ERR);
		}
		return resultMap;
	}
    /**
	 * 로그아웃
	 */
    @ApiOperation(value = "users", notes = "get : logout", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(code = Code.CODE_RESPONSE_SUCESS, message = Message.MSG_SUCCESS)})
	@RequestMapping(path = Path.USER_LOGOUT, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> logout(HttpServletRequest request,	HttpServletResponse response,
			@ApiParam(name = "user data", defaultValue="{\"tokenUuid\" : \"uuid\"}", value ="user logout", required = true)@RequestBody User user){
    	Map<String, Object> resultMap = new HashMap<>();
    	try {
    		User result = userRepository.findOneByTokenUuid(user.getTokenUuid());
    		if (result != null) {
    			result.setTokenUuid("");
    			userRepository.save(result);
    			
    			resultMap.put("resultCode", Code.CODE_SUCCESS);
    			resultMap.put("resultMessage", Message.MSG_SUCCESS);
    		} else {
    			resultMap.put("resultCode", Code.CODE_LOGIN_NON_VALID);
    			resultMap.put("resultMessage", Message.MSG_LOGIN_NON_VALID);
    		}
		} catch (Exception e) {
			resultMap.put("resultCode", Code.CODE_SERVER_ERR);
			resultMap.put("resultMessage", Message.MSG_SERVER_ERR);
		}
    	return resultMap;
	}
    
}
