package com.spc.jpa.domain.onetoone;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(path = "celluars", itemResourceRel = "culluar", collectionResourceRel = "celluars")
public interface CelluarRepository extends JpaRepository<Cellular, String> {

	public Page<Cellular> findAll(Pageable pageable);
	
}
