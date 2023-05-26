package com.example.be.controller;

import com.example.be.entity.ProductCart;
import com.example.be.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
@CrossOrigin(origins = "*")
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping("/add_to_cart")
    public ResponseEntity<?> addToCart(@RequestBody ProductCart productCart){
        try {
            ProductCart productCart1 = cartService.saveNewProductCart(productCart);
            return new ResponseEntity<>(productCart1, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
