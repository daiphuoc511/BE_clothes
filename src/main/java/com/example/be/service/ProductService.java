package com.example.be.service;

import com.example.be.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProductByColor(String color1, String color2, String color3, String color4, String color5, String color6);
    List<Product> getProductByColorAndClothesType(String color1, String color2, String color3, String color4, String color5, String color6, String color7, String color8, Integer clothesType1, Integer clothesType2);
    List<Product> getProductByClothesType(Integer id1, Integer id2);
    List<Product> getProductByCategory_CategoryName(String name);
    Product updateProductByProductId(Integer s, Integer m, Integer l, Integer xl, Integer xxl, Integer xxxl, Integer productId);
    List<Product> getProductByClothesTypeAndSGreaterThan(Integer clothesType1, Integer clothesType2, Integer s);
    List<Product> getProductByClothesTypeAndMGreaterThan(Integer clothesType1, Integer clothesType2, Integer m);
    List<Product> getProductByClothesTypeAndLGreaterThan(Integer clothesType1, Integer clothesType2, Integer l);
    List<Product> getProductByClothesTypeAndXlGreaterThan(Integer clothesType1, Integer clothesType2, Integer xl);
    List<Product> getProductByClothesTypeAndXxlGreaterThan(Integer clothesType1, Integer clothesType2, Integer xxl);
    List<Product> getProductByClothesTypeAndXxxlGreaterThan(Integer clothesType1, Integer clothesType2, Integer xxxl);
    List<Product> getProductBySGreaterThanAndCategory_CategoryName(Integer s, String categoryName);
    List<Product> getProductByMGreaterThanAndCategory_CategoryName(Integer m, String categoryName);
    List<Product> getProductByLGreaterThanAndCategory_CategoryName(Integer l, String categoryName);
    List<Product> getProductByXlGreaterThanAndCategory_CategoryName(Integer xl, String categoryName);
    List<Product> getProductByXxlGreaterThanAndCategory_CategoryName(Integer xxl, String categoryName);
    List<Product> getProductByXxxlGreaterThanAndCategory_CategoryName(Integer xxxl, String categoryName);
}
