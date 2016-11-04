package com.spc.jpa.domain.manytomany;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(path = "categries", itemResourceRel = "category", collectionResourceRel = "categries")
public interface CategoryRepository extends JpaRepository<Category, String> {

	public Page<Category> findAll(Pageable pageable);
	
}
