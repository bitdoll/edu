package com.spc.jpa.domain.relmapping;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.spc.jpa.AbstractIntegrationTest;
import com.spc.jpa.domain.onetoone.CelluarRepository;
import com.spc.jpa.domain.onetoone.Cellular;
import com.spc.jpa.domain.onetoone.Person;
import com.spc.jpa.domain.onetoone.PersonRepository;

/**
 * @author lKJ
 */
@Transactional
@Rollback
public class OneToOneTest extends AbstractIntegrationTest{
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private CelluarRepository cellularRepository;
	

	@Test
	public void oneToOneTest() {
		Cellular cellular = new Cellular();
		cellular.setNumber("1");
		cellularRepository.save(cellular);
		

		Person person = new Person();
		person.setName("SPC");
		person.setCellular(cellular);
		personRepository.save(person);

		Assert.assertEquals(person.getCellular().getId(), cellular.getId());
	}

}
