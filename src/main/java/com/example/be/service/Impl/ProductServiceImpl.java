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
    public List<Product> getAllProduct() {
        return productRepository.getAllProduct();
    }
}
