package com.spc.jpa.domain.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(path = "users", itemResourceRel = "user", collectionResourceRel = "users", excerptProjection = BasicUserProjection.class)
public interface UserRepository extends JpaRepository<User, String> {

	/**
	 * 로그인
	 * 
	 * @param User
	 * @return User
	 */
	User findByEmailAndPassword(String email, String password);
	
	/**
	 * 로그아웃
	 * 
	 * @param User
	 * @return User
	 */
	User findOneByTokenUuid(String tokenUuid);
	
	/**
	 * 동일 아이디 갯수
	 * 
	 * @param id
	 * @return
	 */
	long countByEmail(String id);

	/**
	 * 사용자 목록 조회
	 * 
	 * @param pageable
	 * @return Page<User>
	 */
	public Page<User> findAll(Pageable pageable);
}
