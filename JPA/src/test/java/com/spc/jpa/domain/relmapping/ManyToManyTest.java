package com.spc.jpa.domain.relmapping;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.spc.jpa.AbstractIntegrationTest;
import com.spc.jpa.domain.manytomany.Category;
import com.spc.jpa.domain.manytomany.CategoryRepository;
import com.spc.jpa.domain.manytomany.Product;
import com.spc.jpa.domain.manytomany.ProductRepository;

/**
 * @author lKJ
 */
@Transactional
@Rollback
public class ManyToManyTest extends AbstractIntegrationTest{
	
	@Autowired
    private CategoryRepository categoryRepository;
	
	@Autowired
    private ProductRepository productRepository;
 
    @Test
    public void manyToManyTest() {
 
        Category category = new Category();
        category.setName("IT");
        categoryRepository.save(category);
 
        Product product = new Product();
        product.setName("MacBook");
        product.setPrice(1000);
 
        product.addCategory(category);
        category.addProduct(product);
        productRepository.save(product);
 
        Assert.assertEquals(product.getProductId(), category.getProducts().get(0).getProductId());
    }

}
