package com.spc.jpa.domain.onetomany;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.spc.jpa.domain.AbstractHistoryEntity;

/**
 * @author lKJ
 */
@Entity
@Table(name = "member")
public class Member extends AbstractHistoryEntity {
	
	@Id 
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "member_id")
    private String memberId;

	@Column(name = "name")
    private String name;
	
	@Column(name = "address")
    private String address;
 
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Order> orders;
 
    // getter and setter
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
    
    // 무한루프 방지..
    public boolean addOrder(Order order) {
        if(orders == null)
            orders = new ArrayList<>();
 
        return this.orders.add(order);
    }

    // toString..
	@Override
	public String toString() {
		return "Member [memberId=" + memberId 
				+ ", name=" + name 
				+ ", address=" + address 
				+ ", orders=" + orders + "]";
	}
    
}
