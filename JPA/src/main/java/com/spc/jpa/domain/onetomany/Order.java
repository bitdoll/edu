package com.spc.jpa.domain.onetomany;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.spc.jpa.domain.AbstractHistoryEntity;

/**
 * @author lKJ
 */
@Entity
@Table(name = "orders")
public class Order extends AbstractHistoryEntity {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "order_id")
    private String orderId;
	
	@Column(name = "item")
	private String item;
	
	@Column(name = "cnt")
	private int cnt;
	
	@Column(name = "price")
	private int price;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "member_id")
	private Member member;
 
    // constructor..
    public Order() {}
 
    public Order(String item, int cnt, int price, Member member) {
		super();
		this.item = item;
		this.cnt = cnt;
		this.price = price;
		this.member = member;
	}

	// getter and setter..
    public String getOrderId() {
    	return orderId;
    }
    
    public void setOrderId(String orderId) {
    	this.orderId = orderId;
    }

	public String getItem() {
		return item;
	}


	public void setItem(String item) {
		this.item = item;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

//	public Member getMember() {
//		return member;
//	}

	public void setMember(Member member) {
		// 기존관계 제거
		if ( this.member != null ) {
			this.member.getOrders().remove(this);
		}
		this.member = member;
	}

	// toString..
	@Override
	public String toString() {
		return "Order [orderId=" + orderId 
				+ ", item=" + item 
				+ ", cnt=" + cnt 
				+ ", price=" + price 
				+ ", member=" + member + "]";
	}
	
}
