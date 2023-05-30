package com.example.be.repository;

import com.example.be.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    @Query(value = "update cart set total_cart = ? where cart_id = ?", nativeQuery = true)
    Cart updateTotalCart(Integer cartId, Float total);
}
