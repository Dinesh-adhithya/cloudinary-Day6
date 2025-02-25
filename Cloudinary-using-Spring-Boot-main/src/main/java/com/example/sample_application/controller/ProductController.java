package com.example.sample_application.controller;

import com.example.sample_application.model.Product;
import com.example.sample_application.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

//    @PostMapping
//    public ResponseEntity<Product> createProduct(@ModelAttribute Product product, @RequestParam("image") MultipartFile file) throws IOException {
//        Product savedProduct = productService.createProduct(product, file);
//        return ResponseEntity.ok(savedProduct);
//    }
@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
public ResponseEntity<Product> createProduct(
        @RequestPart(value = "data") String productData,
        @RequestPart(value = "image") MultipartFile file
) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    Product product = mapper.readValue(productData, Product.class);
    return ResponseEntity.ok(productService.createProduct(product, file));
}
}
