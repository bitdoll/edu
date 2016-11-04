package com.spc.jpa.domain.onetoone;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.spc.jpa.domain.AbstractHistoryEntity;

@Entity
@Table(name = "cellular")
public class Cellular extends AbstractHistoryEntity {

	@Column(name = "cellular_id")
    @GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Id
	private String id;

	@Column(name = "number")
	private String number;

	// constructors..
	public Cellular() {
	}

	public Cellular(String number) {
		this.number = number;
	}

	// getter and setter..
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	// toString..
	@Override
	public String toString() {
		return "Cellular{" + "id=" + id + ", number=" + number + '}';
	}

}