package com.spc.jpa.domain.board;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.spc.jpa.AbstractIntegrationTest;
import com.spc.jpa.domain.board.Board.BoardType;

/**
 * @author lKJ
 */
@Transactional
@Rollback
public class BoardRepositoryTest extends AbstractIntegrationTest{

	@Inject
	BoardRepository boardRepository;

	@Test
	@Rollback(false)
	public void testBoard() throws Exception {

		String id = "bliex";

		// findAll
		Page<Board> list = (Page<Board>) boardRepository.findAll(new PageRequest(0,5)); // page, size
		assertNotNull(list);		


		Board board = new Board();
		board.setBoardType(BoardType.NOTICE_BOARD);
		board.setId(id);
		board.setTitle("title");
		board.setContents("content");

		// insert
		Board savedboard = boardRepository.save(board);
		assertEquals("title", savedboard.getTitle());


		// update
		board.setBoardType(BoardType.FREE_BOARD);
		board.setTitle("테스트");
		savedboard = boardRepository.save(board);
		assertEquals("title", savedboard.getTitle());

		// delete
		boardRepository.delete(board.getId());

	}
}
