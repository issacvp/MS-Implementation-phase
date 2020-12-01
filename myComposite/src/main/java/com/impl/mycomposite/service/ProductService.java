package com.impl.mycomposite.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.impl.mycomposite.model.Product;

@Service
@FeignClient(name="PRODUCT-MICROSERVICE")
public interface ProductService {
	@PostMapping("/addProduct")
	public String addProduct(@RequestBody Product product);
	
	@GetMapping(path="/products")
	public String getProducts();
	
	@GetMapping(path="/products/{productId}")
	public Product getProduct(@PathVariable(value = "productId") int productId);
	
	@PutMapping("/products/{productId}")
	public String updateProduct(@PathVariable(value = "productId") int productId,@RequestBody Product product);
	
	@DeleteMapping(path="/deleteAll")
	public String deleteAll();
	
	@DeleteMapping("deleteProduct/{productId}")
	public String deleteProduct(@PathVariable("productId") int productId);
}
