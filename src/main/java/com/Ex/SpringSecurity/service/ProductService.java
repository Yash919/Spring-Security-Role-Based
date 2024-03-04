package com.Ex.SpringSecurity.service;

import com.Ex.SpringSecurity.dto.Product;
import com.Ex.SpringSecurity.entity.UserInfo;
import com.Ex.SpringSecurity.repository.UserInfoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class  ProductService {

    List<Product> productList = null;
    private int productIdCounter = 101;

    @Autowired
    private UserInfoRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void loadProductsFromDB() {
        productList = IntStream.rangeClosed(1, 100)
                .mapToObj(i -> Product.builder()
                        .productId(i)
                        .name("product " + i)
                        .qty(new Random().nextInt(10))
                        .price(new Random().nextInt(5000)).build()
                ).collect(Collectors.toList());
    }


    public List<Product> getProducts() {
        return productList;
    }

    public Product getProduct(int id) {
        return productList.stream()
                .filter(product -> product.getProductId() == id)
                .findAny()
                .orElse(null);
    }

    public String addUser(UserInfo userInfo){
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        repository.save(userInfo);
        return "user added to system";
    }

    public boolean updateProduct(Product product, int id) {

        for (Product existingProduct : productList) {
            if (existingProduct.getProductId() == id) {
                // Update product details
                existingProduct.setName(product.getName());
                existingProduct.setQty(product.getQty());
                existingProduct.setPrice(product.getPrice());
                return true; // Return true indicating successful update
            }
        }
        return false;
    }

    public boolean deleteProduct(int id) {
        Iterator<Product> iterator = productList.iterator();
        while(iterator.hasNext()){
            Product existProduct = iterator.next();
            if(existProduct.getProductId() == id){
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public ResponseEntity<?> addProduct(Product product) {
        // Check if name is empty or not.
        if (product.getName() == null || product.getName().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product name cannot be empty. Failed to add product");
        }
        product.setProductId(productIdCounter++);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product added successfully with Id: " + product.getProductId());
    }
}
