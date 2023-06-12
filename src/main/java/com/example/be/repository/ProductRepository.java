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

    @Query(value = "select * from product where color in (?1, ?2, ?3, ?4) and  clothes_type in (?5, ?6)", nativeQuery = true)
    List<Product> getProductByColorAndClothesType(String color1, String color2, String color3, String color4, Integer clothesType1, Integer clothesType2);

    @Query(value = "select * from product where clothes_type in (?1, ?2)", nativeQuery = true)
    List<Product> getProductByClothesType(Integer id1, Integer id2);

    List<Product> getProductByCategory_CategoryName(String name);

    @Query(value = "select * from product where clothes_type in (?1, ?2) and s > ?3", nativeQuery = true)
    List<Product> getProductByClothesTypeAndSGreaterThan(Integer clothesType1, Integer clothesType2, Integer s);

    @Query(value = "select * from product where clothes_type in (?1, ?2) and m > ?3", nativeQuery = true)
    List<Product> getProductByClothesTypeAndMGreaterThan(Integer clothesType1, Integer clothesType2, Integer m);

    @Query(value = "select * from product where clothes_type in (?1, ?2) and l > ?3", nativeQuery = true)
    List<Product> getProductByClothesTypeAndLGreaterThan(Integer clothesType1, Integer clothesType2, Integer l);

    @Query(value = "select * from product where clothes_type in (?1, ?2) and xl > ?3", nativeQuery = true)
    List<Product> getProductByClothesTypeAndXlGreaterThan(Integer clothesType1, Integer clothesType2, Integer xl);

    @Query(value = "select * from product where clothes_type in (?1, ?2) and xxl > ?3", nativeQuery = true)
    List<Product> getProductByClothesTypeAndXxlGreaterThan(Integer clothesType1, Integer clothesType2, Integer xxl);

    @Query(value = "select * from product where clothes_type in (?1, ?2) and xxxl > ?3", nativeQuery = true)
    List<Product> getProductByClothesTypeAndXxxlGreaterThan(Integer clothesType1, Integer clothesType2, Integer xxxl);

    List<Product> getProductBySGreaterThanAndCategory_CategoryName(Integer s, String categoryName);

    List<Product> getProductByMGreaterThanAndCategory_CategoryName(Integer m, String categoryName);

    List<Product> getProductByLGreaterThanAndCategory_CategoryName(Integer l, String categoryName);

    List<Product> getProductByXlGreaterThanAndCategory_CategoryName(Integer xl, String categoryName);

    List<Product> getProductByXxlGreaterThanAndCategory_CategoryName(Integer xxl, String categoryName);

    List<Product> getProductByXxxlGreaterThanAndCategory_CategoryName(Integer xxxl, String categoryName);

    @Query(value = "update product set s = ?1, m = ?2, l = ?3, xl = ?4, xxl = ?5, xxxl = ?6 where product_id = ?7", nativeQuery = true)
    Product updateProductByProductId(Integer s, Integer m, Integer l, Integer xl, Integer xxl, Integer xxxl, Integer productId);
}
