package com.spc.jpa.domain.manytomany;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(path = "products", itemResourceRel = "product", collectionResourceRel = "products")
public interface ProductRepository extends JpaRepository<Product, String> {

	public Page<Product> findAll(Pageable pageable);
	
}
