package com.example.be.service;

import com.example.be.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProductByColor(String color1, String color2, String color3, String color4, String color5, String color6);
}
