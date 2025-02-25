package com.example.sample_application.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.sample_application.model.Product;
import com.example.sample_application.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class ProductService {


    private Cloudinary cloudinary;

    public ProductService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product, MultipartFile file) throws IOException {
        if (file != null) {
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            product.setImageUrl(uploadResult.get("secure_url").toString());
        }
        return productRepository.save(product);
    }
}
