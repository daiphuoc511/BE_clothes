package com.example.be.service.Impl;

import com.example.be.entity.Cart;
import com.example.be.entity.ProductCart;
import com.example.be.repository.CartRepository;
import com.example.be.repository.ProductCartRepository;
import com.example.be.service.CartService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CartServiceImpl implements CartService {

    @Autowired
    ProductCartRepository productCartRepository;

    @Autowired
    CartRepository cartRepository;

    @Override
    public ProductCart saveNewProductCart(ProductCart productCart) {
        return productCartRepository.save(productCart);
    }

    @Override
    public List<ProductCart> getProductCartByCartId(Integer cartId) {
        return productCartRepository.getProductCartByCart_CartId(cartId);
    }

    @Override
    public Cart updateTotalCart(Integer cartId, Float total) {
        return cartRepository.updateTotalCart(cartId, total);
    }

}
