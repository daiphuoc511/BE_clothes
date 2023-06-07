package com.example.be.repository;

import com.example.be.entity.ProductCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCartRepository extends JpaRepository<ProductCart, Integer> {

    List<ProductCart> getProductCartByCart_CartId(Integer cartId);

}
