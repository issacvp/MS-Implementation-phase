package com.impl.mycomposite.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.impl.mycomposite.model.Product;
import com.impl.mycomposite.service.ProductService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class ProductCompositeController {
	@Autowired
	ProductService prdService;

	@PostMapping("/addProduct")
    @HystrixCommand(fallbackMethod = "customerAddProducts")
	public String addProduct(@RequestBody Product product) {
    	return prdService.addProduct(product);
    }
    
    @GetMapping(path="/products")
	public String getProducts() {
    	return prdService.getProducts();
    }
    
    @GetMapping(path="/products/{productId}")
    @HystrixCommand(fallbackMethod = "getByPrdId")
    public String getProduct(@PathVariable("productId") int productId) {
    	return prdService.getProduct(productId).toString();
    }
    
    @PutMapping("/products/{productId}")
    @HystrixCommand(fallbackMethod = "getByPrdIdUpd")
	public String updateProduct(@PathVariable("productId") int productId,@RequestBody Product product) {
    	return prdService.updateProduct(productId, product);
    }
    
    @DeleteMapping(path="/deleteAll")
    @HystrixCommand(fallbackMethod = "deleteFallback")
	public String deleteAll() {
    	return prdService.deleteAll();
    }
    
    @DeleteMapping("/deleteProduct/{productId}")
    @HystrixCommand(fallbackMethod = "prdDelById")
	public String deleteProduct(@PathVariable("productId") int productId) {
    	return prdService.deleteProduct(productId);
    }
    
    
    @SuppressWarnings("unused")
    private String customerAddProducts(@RequestBody Product product) { 
        return new String("CIRCUIT BREAKER ENABLED!!! No Response From product Service at this moment. " +
                    " Service will be back shortly - " + new Date());
    }
    
    
    
    @SuppressWarnings("unused")
    private String productGetProduct() { 
        return new String ("CIRCUIT BREAKER ENABLED!!! No Response From product Service at this moment. " +
                    " Service will be back shortly - ");
    }
    
    
    
    @SuppressWarnings("unused")
    private String getByPrdId(@PathVariable("customerId") int customerId) { 
        return new String ("CIRCUIT BREAKER ENABLED!!! No Response From product Service at this moment. " +
                    " Service will be back shortly - ");
    }
    
    @SuppressWarnings("unused")
    private String getByPrdIdUpd(@PathVariable int productId,@RequestBody Product product) { 
    	return new String ("CIRCUIT BREAKER ENABLED!!! No Response From product Service at this moment. " +
                " Service will be back shortly - ");
    }
    
    
    
    @SuppressWarnings("unused")
    private String prdDelById(@PathVariable("productId") int productId) { 
        return new String ("CIRCUIT BREAKER ENABLED!!! No Response From product Service at this moment. " +
                " Service will be back shortly - ");
    }
}
