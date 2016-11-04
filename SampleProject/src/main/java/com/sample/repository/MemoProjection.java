package com.sample.repository;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "list", types = { Memo.class })
public interface MemoProjection {
		
	public String getId();
	
	public String getTitle();
	
	public String getContent();

}