package com.spc.jpa.domain.manytomany;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.spc.jpa.domain.AbstractHistoryEntity;

/**
 * @author lKJ
 */
@Entity
@Table(name = "category")
public class Category extends AbstractHistoryEntity {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "category_id")
    private String categoryId;
 
	@Column(name = "name")
    private String name;
 
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "categories")
    private List<Product> products;
 
    // constructor..
    public Category() {
    }
 
    public Category(String name, List<Product> products) {
        this.name = name;
        this.products = products;
    }
 
    // getter and setter..
    public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	// 무한루프 방지..
    public boolean addProduct(Product product) {
        if(products == null)
            products = new ArrayList<>();
 
        return products.add(product);
    }

    //toString..
	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId 
				+ ", name=" + name 
				+ ", products=" + products + "]";
	}
    
}
