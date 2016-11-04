package com.spc.jpa.domain.board;

import org.joda.time.DateTime;
import org.springframework.data.rest.core.config.Projection;

/**
 * @author lKJ
 */
@Projection(name = "basic", types = { Board.class })
public interface BasicBoardProjection {

	String getId();
	  
	String getTitle();
	  
	String getContents();
	
	String getBoardType();
	  
	String getCreatedBy();
	  
	DateTime getCreatedTime();
	  
	String getModifiedBy();
	  
	DateTime getModifiedTime();

}
