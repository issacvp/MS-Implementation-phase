package com.impl.product.product.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.impl.product.product.entity.Product;
import com.impl.product.product.service.ProductService;

@RefreshScope
@RestController
public class ProductController {
	
	@Value("${server.port}")
	private int port;
	
	@Autowired
	ProductService productService;
	
	@PostMapping("/addProduct")
	public ResponseEntity<Object> addProduct(@RequestBody Product product){
		System.out.println("addProduct Request getting served by port:"+port);
		Product savedProduct=productService.saveData(product);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedProduct.getProductId()).toUri();
        return ResponseEntity.created(location).build();
	}
	
    @GetMapping(path="/products")
    public ResponseEntity<Object> getProducts() {
    	System.out.println("getProducts Request getting served by port:"+port);
    	List<Product> list = productService.findAll();
    	if (list.size() > 0) {
    		 return new ResponseEntity<Object>(list, HttpStatus.ACCEPTED);
    	} else {
    		return new ResponseEntity<Object>("No products found!", HttpStatus.ACCEPTED);
    	}
    }
    
    @GetMapping(path="/products/{productId}")
    public ResponseEntity<Object> getProduct(@PathVariable("productId") int productId) {
    	System.out.println("getProduct Request getting served by port:"+port);
    	Optional<Product> prd = productService.findBy(productId);
    	if (prd.isPresent()) {
    		return new ResponseEntity<Object>(prd.get(), HttpStatus.OK);
    	} else {
    		return new ResponseEntity<Object>("Product Details not found!", HttpStatus.NOT_FOUND);
    	}
    }
    
    @PutMapping("/products/{productId}")
    public ResponseEntity<Object> updateProduct(@PathVariable int productId,@RequestBody Product product) {
    	System.out.println("updateProduct Request getting served by port:"+port);
	    Product updatedProduct=productService.updateProduct(productId, product);
	    if(updatedProduct!=null) {
	    	return new ResponseEntity<Object>(updatedProduct, HttpStatus.OK);
	    } else {
			return new ResponseEntity<Object>("Product updation failed!", HttpStatus.NOT_FOUND);
		}
    }
        
   @DeleteMapping(path="/deleteAll")
   public ResponseEntity<Object> deleteAll() {
	   System.out.println("deleteAll Request getting served by port:"+port);
       return new ResponseEntity<Object>(productService.deleteAll(), HttpStatus.ACCEPTED);
   }
    
    @DeleteMapping("deleteProduct/{productId}")
    public ResponseEntity<Object> deleteProduct(@PathVariable("productId") int productId) {
    	System.out.println("deleteProduct Request getting served by port:"+port);
    	return new ResponseEntity<Object>(productService.deleteProduct(productId), HttpStatus.ACCEPTED);
    }
	
	
}
