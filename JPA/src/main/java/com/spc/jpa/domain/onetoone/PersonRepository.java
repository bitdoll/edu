package com.spc.jpa.domain.onetoone;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(path = "persons", itemResourceRel = "person", collectionResourceRel = "persons")
public interface PersonRepository extends JpaRepository<Person, String> {


	public Page<Person> findAll(Pageable pageable);
	
}
