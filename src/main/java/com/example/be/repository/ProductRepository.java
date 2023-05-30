package com.example.be.repository;

import com.example.be.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "select * from product where color in (?1, ?2, ?3, ?4, ?5, ?6)", nativeQuery = true)
    List<Product> getProductByColor(String color1, String color2, String color3, String color4, String color5, String color6);

    @Query(value = "select * from product where color in (?1, ?2, ?3, ?4) and  clothes_type = ?5", nativeQuery = true)
    List<Product> getProductByColorAndClothesType(String color1, String color2, String color3, String color4, Integer clothesType);

    @Query(value = "select * from product where clothes_type = ?1", nativeQuery = true)
    List<Product> getProductByClothesType(Integer id);

    List<Product> getProductByCategory_CategoryName(String name);

    @Query(value = "update product set s = ?1, m = ?2, l = ?3, xl = ?4, xxl = ?5, xxxl = ?6 where product_id = ?7", nativeQuery = true)
    Product updateProductByProductId(Integer s, Integer m, Integer l, Integer xl, Integer xxl, Integer xxxl, Integer productId);
}
