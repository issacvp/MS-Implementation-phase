package com.impl.product.product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.impl.product.product.entity.Product;
import com.impl.product.product.repository.ProductReporsitory;

@Service
public class ProductService {
	
	@Autowired
	ProductReporsitory prodRepo;
	
	public Product saveData(Product product)
    {
        prodRepo.save(product);
        return product;
    }
	
	public List<Product> findAll()
    {
        return prodRepo.findAll();
    }
	
    public Optional<Product> findBy(int productId)
    {
        return prodRepo.findById(productId);
    }
    
    public Product updateProduct(int productId, Product product) {
        Optional<Product> prod=prodRepo.findById(productId);
        Product prd = null;
        if (prod.isPresent()) {
        	prd = prod.get();
        	prd.setProductPrice(product.getProductPrice());
            prd.setProductName(product.getProductName());
            prodRepo.save(prd);
        } else {
        	prd = null;
        }
        return prd;
    }
    public String deleteAll()
    {
        prodRepo.deleteAll();
        return "All product data are Deleted";
    }
    
    public String deleteProduct(int productId) {
    	prodRepo.deleteById(productId);
    	return "Product deleted";
    }
}
