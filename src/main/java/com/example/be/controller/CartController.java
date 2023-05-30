package com.example.be.controller;

import com.example.be.entity.Cart;
import com.example.be.entity.ProductCart;
import com.example.be.entity.User;
import com.example.be.service.CartService;
import com.example.be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
@CrossOrigin(origins = "*")
public class CartController {

    @Autowired
    CartService cartService;

    @Autowired
    UserService userService;

    @PostMapping("/add_to_cart")
    public ResponseEntity<?> addToCart(@RequestBody ProductCart productCart){
        try {
            ProductCart productCart1 = cartService.saveNewProductCart(productCart);
            return new ResponseEntity<>(productCart1, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/get_cart")
    public ResponseEntity<List<ProductCart>> getCartByUser(Authentication authentication){
        String username = authentication.getName();
        try {
            User user1 = userService.findByUser(username);
            List<ProductCart> productCarts = cartService.getProductCartByCartId(user1.getCart().getCartId());
            return new ResponseEntity<>(productCarts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/update_cart/{cartId}")
    public ResponseEntity<?> updateCartByUser(@RequestBody Float cartTotal, @PathVariable("cartId") Integer cartId) {
        try {
            Cart cart = cartService.updateTotalCart(cartId, cartTotal);
            return new ResponseEntity<>(cart, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
