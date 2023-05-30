package com.example.be.service;

import com.example.be.entity.Cart;
import com.example.be.entity.ProductCart;

import java.util.List;

public interface CartService {
    ProductCart saveNewProductCart(ProductCart productCart);
    List<ProductCart> getProductCartByCartId(Integer cartId);
    Cart updateTotalCart(Integer cartId, Float total);
}
