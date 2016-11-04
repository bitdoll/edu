package com.spc.jpa.domain.board;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.spc.jpa.domain.AbstractHistoryEntity;
import com.spc.jpa.domain.user.User;

/**
 * @author lKJ
 */
@Entity
@Table(name = "boards")
public class Board extends AbstractHistoryEntity{

	public enum BoardType{
		NOTICE_BOARD,
		ALERT_BOARD,
		FREE_BOARD,
		GALLERY_BOARD
	}

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "board_id", columnDefinition="varchar(50)")
	String id;


	@Column(name = "board_type", columnDefinition="varchar(50)")
	@Enumerated(EnumType.STRING)
	private BoardType boardType;

	@Column(name = "title", columnDefinition="varchar(1000)")
	private String title;

	@Lob
	@Column(name = "contents")
	private String contents;
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name = "user_id")
	private User user;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public BoardType getBoardType() {
		return boardType;
	}
	
	public void setBoardType(BoardType boardType) {
		this.boardType = boardType;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContents() {
		return contents;
	}
	
	public void setContents(String contents) {
		this.contents = contents;
	}
	
//	public User getUser() {
//		return user;
//	}

	public void setUser(User user) {
		// 기존 관계 제거
		this.user = user;
	}

}
