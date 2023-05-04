package com.example.be.service.Impl;

import com.example.be.entity.Product;
import com.example.be.repository.ProductRepository;
import com.example.be.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getProductByColor(String color1, String color2, String color3, String color4, String color5, String color6) {
        return productRepository.getProductByColor(color1, color2, color3, color4, color5, color6);
    }
}
