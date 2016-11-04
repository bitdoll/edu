package com.spc.jpa.domain.board;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.spc.jpa.domain.board.Board.BoardType;


@RepositoryRestResource(path = "boards", itemResourceRel = "board", collectionResourceRel = "boards", excerptProjection = BasicBoardProjection.class)
public interface BoardRepository extends JpaRepository<Board, String> {

	/**
	 * 게시글 목록 조회 (페이징)
	 * 
	 * @param pageable
	 * @return Page<Board>
	 */
	public Page<Board> findAll(Pageable pageable);
	
	/**
	 * 게시글 목록 조회 (페이징)
	 * 
	 * @param pageable
	 * @return Page<Board>
	 */
	public Page<Board> findByBoardTypeOrderByCreatedTimeDescTitleDesc(Pageable pageable, @Param("board_type")BoardType boardType);
	
	/**
	 * 게시글 목록 조회 (정렬)
	 * 
	 * @param Sort
	 * @return List<Board>
	 */
	public List<Board> findAll(Sort sort);
	
	/**
	 * 게시글 목록 조회 (페이징, 검색)
	 * 
	 * @param pageable
	 * @return Page<Board>
	 */
	public Page<Board> findByTitleContainingAndBoardTypeOrderByCreatedTimeDescTitleDesc(Pageable pageable, @Param("title")String title, @Param("board_type")BoardType boardType);
	
	/**
	 * 게시글 목록 조회 (페이징, 검색)
	 * 
	 * @param pageable
	 * @return Page<Board>
	 */
	public Page<Board> findByContentsContainingAndBoardTypeOrderByCreatedTimeDescTitleDesc(Pageable pageable, @Param("contents")String contents, @Param("board_type")BoardType boardType);
	
}
