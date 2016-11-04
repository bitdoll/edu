package com.spc.jpa.domain.onetoone;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.spc.jpa.domain.AbstractHistoryEntity;

@Entity
@Table(name = "person")
public class Person extends AbstractHistoryEntity {
 
    @Id
    @GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "person_id")
    private String personId;
 
    @Column(name = "name")
    private String name;
 
    @OneToOne
    @JoinColumn(name = "cellular_id")
    private Cellular cellular;
 
    // constructors..
    public Person() {}
 
    public Person(String name, Cellular cellular) {
        this.name = name;
        this.cellular = cellular;
    }
 
    // getter and setter..
	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Cellular getCellular() {
		return cellular;
	}

	public void setCellular(Cellular cellular) {
		this.cellular = cellular;
	}

	// toString..
	@Override
	public String toString() {
		return "Person [personId=" + personId 
				+ ", name=" + name 
				+ ", cellular=" + cellular + "]";
	}
 
    
}