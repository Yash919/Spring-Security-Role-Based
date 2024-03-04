package com.Ex.SpringSecurity.controller;

import com.Ex.SpringSecurity.dto.Product;
import com.Ex.SpringSecurity.entity.UserInfo;
import com.Ex.SpringSecurity.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome this endpoint is not secure (Open for everyone)";
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Product> getAllTheProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<?> getProductById(@PathVariable int id) {

        Product product = productService.getProduct(id);
        if(product!=null){
            return ResponseEntity.ok(product);
        }
        String errormessage = "Product with ID " + id + " not found";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errormessage);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> updateProduct(@RequestBody Product product, @PathVariable int id){
        boolean isProductUpdated =  productService.updateProduct(product,id);

        if(isProductUpdated){
            return new ResponseEntity<>("Product with ID: "+ id +". Updated Successfully. ", HttpStatus.OK);
        }
        return new ResponseEntity<>("Product not updated. No Product found with ID " + id,HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        boolean isProductPresent = productService.deleteProduct(id);

        if(isProductPresent){
            return new ResponseEntity<>("Product with id: "+id+" Deleted Successfully.",HttpStatus.OK);
        }
        return new ResponseEntity<>("Product with id: "+id+" not present.",HttpStatus.NOT_FOUND);
    }

    @PostMapping("/new")
    public String addNewUser(@RequestBody UserInfo userInfo){
        return productService.addUser(userInfo);
    }

}

