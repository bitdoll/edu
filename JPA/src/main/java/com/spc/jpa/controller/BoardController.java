package com.spc.jpa.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spc.jpa.common.Constant.Code;
import com.spc.jpa.common.Constant.Message;
import com.spc.jpa.common.Path;
import com.spc.jpa.domain.board.Board;
import com.spc.jpa.domain.board.BoardInfo;
import com.spc.jpa.domain.board.BoardInfo.SearchType;
import com.spc.jpa.domain.board.BoardRepository;
import com.spc.jpa.domain.user.User;
import com.spc.jpa.domain.user.UserRepository;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@RestController
public class BoardController {
	
	@Autowired
	BoardRepository boardRepository;
	
	@Autowired
	UserRepository userRepository;
	
    /**
	 * 게시판 조회 (검색, 페이징)
	 */
    @ApiOperation(value = "boards", notes = "post : boards", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(code = Code.CODE_RESPONSE_SUCESS, message = Message.MSG_SUCCESS)})
	@RequestMapping(path = Path.BOARD_LIST, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> findBoards(@ApiParam(name = "uuid", defaultValue="test_user_token_uuid", value ="login token uuid", required = true)@RequestHeader(value="uuid") String uuid
			,@ApiParam(name = "BoardInfo", defaultValue="{\"boardType\" : \"NOTICE_BOARD\", \"page\" : \"1\", \"size\":\"10\",\"searchType\":\"TITLE\",\"searchKeyword\":\"\"}", value ="paging and searching data", required = true) @RequestBody BoardInfo info) {
    	
    	Map<String, Object> resultMap = new HashMap<>();
    	try {    		
    		Page<Board> result;
	    	if( info.getSearchKeyword().equals( "" )){
	    			result = boardRepository.findByBoardTypeOrderByCreatedTimeDescTitleDesc(new PageRequest(info.getPage()-1, info.getSize()), info.getBoardType());
	    	}else{
	    		if ( info.getSearchType() == SearchType.TITLE ) {
	    			result = boardRepository.findByTitleContainingAndBoardTypeOrderByCreatedTimeDescTitleDesc(new PageRequest(info.getPage()-1, info.getSize()), info.getSearchKeyword(), info.getBoardType());
	    		} else {
	    			result = boardRepository.findByContentsContainingAndBoardTypeOrderByCreatedTimeDescTitleDesc(new PageRequest(info.getPage()-1, info.getSize()), info.getSearchKeyword(), info.getBoardType());
	    		}
	    	}
	    	if (result != null) {
	    		resultMap.put("resultCode", Code.CODE_SUCCESS);
				resultMap.put("resultMessage", Message.MSG_SUCCESS);
	    		resultMap.put("data", result);
	    	}
    	} catch (Exception e) {
    		resultMap.put("resultCode", Code.CODE_SERVER_ERR);
			resultMap.put("resultMessage", Message.MSG_SERVER_ERR);
    		return resultMap;
		}
		return resultMap;
	}
    
    /**
	 * 게시글 조회 
	 */
    @ApiOperation(value = "boards", notes = "post : board detail", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(code = Code.CODE_RESPONSE_SUCESS, message = Message.MSG_SUCCESS)})
	@RequestMapping(path = Path.BOARD_DETAIL, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> findBoardDetail(HttpServletRequest request, HttpServletResponse response
			,@ApiParam(name = "uuid", defaultValue="test_user_token_uuid", value ="login token uuid", required = true)@RequestHeader(value="uuid") String uuid
			,@ApiParam(name = "Board", defaultValue="{\"id\":\"board_id\"}", value ="board data", required = true) @RequestBody Board board) {
    	
    	Map<String, Object> resultMap = new HashMap<>();
    	try {    		
    		Board result = boardRepository.findOne(board.getId());
    		if ( result != null ) {
    			resultMap.put("resultCode", Code.CODE_SUCCESS);
    			resultMap.put("resultMessage", Message.MSG_SUCCESS);
    			resultMap.put("data", result);
    			
    			User user = userRepository.findOneByTokenUuid(uuid);
    			
    			if ( result.getCreatedBy().equals(user.getId())) {
    				resultMap.put("auth", 'Y');
    			} else {
    				resultMap.put("auth", 'N');
    			}
    		} else {
    			resultMap.put("resultCode", Code.CODE_BOARD_NON_VALID);
    			resultMap.put("resultMessage", Message.MSG_BOARD_NON_VALID);
    		}
    	} catch (Exception e) {
    		resultMap.put("resultCode", Code.CODE_SERVER_ERR);
			resultMap.put("resultMessage", Message.MSG_SERVER_ERR);
    		return resultMap;
		}
		return resultMap;
	}
    
    /**
	 * 게시글 등록
	 */
    @ApiOperation(value = "boards", notes = "post : boards", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(code = Code.CODE_RESPONSE_SUCESS, message = Message.MSG_SUCCESS)})
	@RequestMapping(path = Path.BOARD_REGISTER, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveBoards(@ApiParam(name = "uuid", defaultValue="test_user_token_uuid", value ="login token uuid", required = true)@RequestHeader(value="uuid") String uuid
			,@ApiParam(name = "boardVO", defaultValue="{\"boardType\" : \"NOTICE_BOARD\", \"title\":\"제목\",\"contents\":\"내용\",\"createdBy\":\"작성자\"}", value ="board data insert", required = true)@RequestBody Board board){
    	
    	Map<String, Object> resultMap = new HashMap<>();
    	try {
    		User user = userRepository.findOneByTokenUuid(uuid);
    		
	    	Board boardVO = new Board();
	    	boardVO.setBoardType(board.getBoardType());
	    	boardVO.setTitle(board.getTitle());
	    	boardVO.setContents(board.getContents());
	    	boardVO.setCreatedBy(user.getId());
	    	boardVO.setModifiedBy(user.getId());
	    	
	    	Board result = boardRepository.save(boardVO);
	    	if (result != null) {
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
     * 게시글 수정
     */
	@ApiOperation(value = "boards", notes = "put : boards", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(code = Code.CODE_RESPONSE_SUCESS, message = Message.MSG_SUCCESS)})
	@RequestMapping(path = Path.BOARD_UPDATE, method = RequestMethod.PUT)
    public @ResponseBody Map<String, Object> updateBoards(@ApiParam(name = "uuid", defaultValue="test_user_token_uuid", value ="login token uuid", required = true)@RequestHeader(value="uuid") String uuid
			,@ApiParam(name = "boardVO", defaultValue="{\"id\" : \"id\", \"title\":\"제목\",\"contents\":\"내용\"}", value ="board data update", required = true)@RequestBody Board board){

    	Map<String, Object> resultMap = new HashMap<>();
    	try {
    		Board boardVO = boardRepository.findOne(board.getId());
    		if (null != boardVO) {
    			if (!"".equals(boardVO.getTitle())) {
    				boardVO.setTitle(board.getTitle());
    			}
    			if (!"".equals(boardVO.getContents())) {
    				boardVO.setContents(board.getContents());
    			}
    			Board result = boardRepository.save(boardVO);
				boardRepository.flush();
				resultMap.put("resultCode", Code.CODE_SUCCESS);
				resultMap.put("resultMessage", Message.MSG_SUCCESS);
				resultMap.put("data", result);
    		} else {
    			resultMap.put("resultCode", Code.CODE_BOARD_NON_VALID);
    			resultMap.put("resultMessage", Message.MSG_BOARD_NON_VALID);
    		}
		} catch (Exception e) {
			resultMap.put("resultCode", Code.CODE_SERVER_ERR);
			resultMap.put("resultMessage", Message.MSG_SERVER_ERR);
		}
    	return resultMap;
    	
	}
    
    /**
     * 게시글 삭제
     */
    @ApiOperation(value = "boards", notes = "delete : boards", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(code = Code.CODE_RESPONSE_SUCESS, message = Message.MSG_SUCCESS)})
	@RequestMapping(path = Path.BOARD_DELETE, method = RequestMethod.DELETE)
    public @ResponseBody Map<String, Object> deleteBoards(@ApiParam(name = "uuid", defaultValue="test_user_token_uuid", value ="login token uuid", required = true)@RequestHeader(value="uuid") String uuid
			,@ApiParam(name = "boardId", defaultValue="{\"id\" : \"id\"}", value ="board data delete", required = true)@RequestBody Board board){
    	
    	Map<String, Object> resultMap = new HashMap<>();
    	try {
    		Board boardVO = boardRepository.findOne(board.getId());
    		if (null != boardVO) {
	    		boardRepository.delete(board.getId());
	    		boardRepository.flush();
	    		resultMap.put("resultCode", Code.CODE_SUCCESS);
				resultMap.put("resultMessage", Message.MSG_SUCCESS);
    		} else {
    			resultMap.put("resultCode", Code.CODE_BOARD_NON_VALID);
    			resultMap.put("resultMessage", Message.MSG_BOARD_NON_VALID);
    		}
		} catch (Exception e) {
			resultMap.put("resultCode", Code.CODE_SERVER_ERR);
			resultMap.put("resultMessage", Message.MSG_SERVER_ERR);
		}
		return resultMap;
		
	}
    
}
