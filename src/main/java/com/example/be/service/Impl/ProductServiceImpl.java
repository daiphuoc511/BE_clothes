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

    @Override
    public List<Product> getProductByColorAndClothesType(String color1, String color2, String color3, String color4, Integer clothesType1, Integer clothesType2) {
        return productRepository.getProductByColorAndClothesType(color1, color2, color3, color4, clothesType1, clothesType2);
    }

    @Override
    public List<Product> getProductByClothesType(Integer id1, Integer id2) {
        return productRepository.getProductByClothesType(id1, id2);
    }

    @Override
    public List<Product> getProductByCategory_CategoryName(String name) {
        return productRepository.getProductByCategory_CategoryName(name);
    }

    @Override
    public Product updateProductByProductId(Integer s, Integer m, Integer l, Integer xl, Integer xxl, Integer xxxl, Integer productId) {
        return productRepository.updateProductByProductId(s, m, l, xl, xxl, xxxl, productId);
    }

    @Override
    public List<Product> getProductByClothesTypeAndSGreaterThan(Integer clothesType1, Integer clothesType2, Integer s) {
        return productRepository.getProductByClothesTypeAndSGreaterThan(clothesType1, clothesType2, s);
    }

    @Override
    public List<Product> getProductByClothesTypeAndMGreaterThan(Integer clothesType1, Integer clothesType2, Integer m) {
        return productRepository.getProductByClothesTypeAndMGreaterThan(clothesType1, clothesType2, m);
    }

    @Override
    public List<Product> getProductByClothesTypeAndLGreaterThan(Integer clothesType1, Integer clothesType2, Integer l) {
        return productRepository.getProductByClothesTypeAndLGreaterThan(clothesType1, clothesType2, l);
    }

    @Override
    public List<Product> getProductByClothesTypeAndXlGreaterThan(Integer clothesType1, Integer clothesType2, Integer xl) {
        return productRepository.getProductByClothesTypeAndXlGreaterThan(clothesType1, clothesType2, xl);
    }

    @Override
    public List<Product> getProductByClothesTypeAndXxlGreaterThan(Integer clothesType1, Integer clothesType2, Integer xxl) {
        return productRepository.getProductByClothesTypeAndXxlGreaterThan(clothesType1, clothesType2, xxl);
    }

    @Override
    public List<Product> getProductByClothesTypeAndXxxlGreaterThan(Integer clothesType1, Integer clothesType2, Integer xxxl) {
        return productRepository.getProductByClothesTypeAndXxxlGreaterThan(clothesType1, clothesType2, xxxl);
    }

    @Override
    public List<Product> getProductBySGreaterThanAndCategory_CategoryName(Integer s, String categoryName) {
        return productRepository.getProductBySGreaterThanAndCategory_CategoryName(s, categoryName);
    }

    @Override
    public List<Product> getProductByMGreaterThanAndCategory_CategoryName(Integer m, String categoryName) {
        return productRepository.getProductByMGreaterThanAndCategory_CategoryName(m, categoryName);
    }

    @Override
    public List<Product> getProductByLGreaterThanAndCategory_CategoryName(Integer l, String categoryName) {
        return productRepository.getProductByLGreaterThanAndCategory_CategoryName(l, categoryName);
    }

    @Override
    public List<Product> getProductByXlGreaterThanAndCategory_CategoryName(Integer xl, String categoryName) {
        return productRepository.getProductByXlGreaterThanAndCategory_CategoryName(xl, categoryName);
    }

    @Override
    public List<Product> getProductByXxlGreaterThanAndCategory_CategoryName(Integer xxl, String categoryName) {
        return productRepository.getProductByXxlGreaterThanAndCategory_CategoryName(xxl, categoryName);
    }

    @Override
    public List<Product> getProductByXxxlGreaterThanAndCategory_CategoryName(Integer xxxl, String categoryName) {
        return productRepository.getProductByXxxlGreaterThanAndCategory_CategoryName(xxxl, categoryName);
    }


}
