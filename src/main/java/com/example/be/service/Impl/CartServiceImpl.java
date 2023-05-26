package com.example.be.service.Impl;

import com.example.be.entity.ProductCart;
import com.example.be.repository.ProductCartRepository;
import com.example.be.service.CartService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CartServiceImpl implements CartService {

    @Autowired
    ProductCartRepository productCartRepository;

    @Override
    public ProductCart saveNewProductCart(ProductCart productCart) {
        return productCartRepository.save(productCart);
    }
}
