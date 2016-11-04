package com.spc.jpa.domain.relmapping;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.spc.jpa.AbstractIntegrationTest;
import com.spc.jpa.domain.onetomany.Member;
import com.spc.jpa.domain.onetomany.MemberRepository;
import com.spc.jpa.domain.onetomany.Order;

/**
 * @author lKJ
 */
@Transactional
@Rollback
public class OneToManyAndManyToOneTest extends AbstractIntegrationTest{

	@Autowired
	private MemberRepository orderRepository;

	@Test
	public void oneToManyAndManyToOneTest() {
		Order order = new Order();
		order.setItem("item");
		order.setPrice(1000);
		order.setCnt(1);
		
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(order);
		
		Member member = new Member();
		member.setAddress("주소");
		member.setName("Name");
		member.setOrders(orderList);
		orderRepository.save(member);
		

		Assert.assertEquals(member.getOrders().get(0).getOrderId(), order.getOrderId());
	}

}
