package com.spc.jpa.domain.manytomany;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.spc.jpa.domain.AbstractHistoryEntity;

/**
 * @author lKJ
 */
@Entity
@Table(name = "product")
public class Product extends AbstractHistoryEntity {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "product_id")
    private String productId;
 
	@Column(name = "name")
    private String name;
 
	@Column(name = "price")
    private int price;
 
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "product_category",
               joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "product_id"),
               inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "category_id"))
    private List<Category> categories;
 
    // constructor..
    public Product() {
    }
 
    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }
 
    // getter and setter..
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

//	public List<Category> getCategories() {
//		return categories;
//	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

    public boolean addCategory(Category category) {
        if(categories == null)
            categories = new ArrayList<>();
 
        return categories.add(category);
    }

    // toString..
	@Override
	public String toString() {
		return "Product [productId=" + productId 
				+ ", name=" + name 
				+ ", price=" + price 
				+ ", categories=" + categories + "]";
	}
    
}
