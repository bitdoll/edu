package com.spc.jpa.domain.onetomany;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(path = "members", itemResourceRel = "member", collectionResourceRel = "members")
public interface MemberRepository extends JpaRepository<Member, String> {

	public Page<Member> findAll(Pageable pageable);
	
}
