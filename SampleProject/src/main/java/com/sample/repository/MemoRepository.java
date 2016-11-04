package com.sample.repository;

import org.springframework.data.domain.Page; 
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "memo", itemResourceRel = "memo", collectionResourceRel = "memo", excerptProjection=MemoProjection.class)
public interface MemoRepository extends JpaRepository<Memo, String> {

	public Page<Memo> findAll(Pageable pageable);
	
}
