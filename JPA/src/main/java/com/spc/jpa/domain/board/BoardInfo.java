package com.spc.jpa.domain.board;

import com.spc.jpa.domain.board.Board.BoardType;

public class BoardInfo {
	public enum SearchType{
		TITLE,
		CONTENTS
	}

	private int page;

	private int size;

	private BoardType boardType;
	
	private SearchType searchType;
	
	private String searchKeyword;

	// getter and setter..
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public BoardType getBoardType() {
		return boardType;
	}

	public void setBoardType(BoardType boardType) {
		this.boardType = boardType;
	}

	public SearchType getSearchType() {
		return searchType;
	}

	public void setSearchType(SearchType searchType) {
		this.searchType = searchType;
	}

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	// toString..
	@Override
	public String toString() {
		return "BoardInfo [page=" + page + ", size=" + size + ", boardType=" + boardType + ", searchType=" + searchType
				+ ", searchKeyword=" + searchKeyword + "]";
	}
	
}
