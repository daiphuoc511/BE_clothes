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
import java.util.Objects;

@RestController
@RequestMapping("api/user")
@CrossOrigin(origins = "*")
public class CartController {

    @Autowired
    CartService cartService;

    @Autowired
    UserService userService;

    @PostMapping("/add_to_cart")
    public ResponseEntity<?> addToCart(@RequestBody ProductCart productCart, Authentication authentication){
        String username = authentication.getName();
        try {
            User user = userService.findByUser(username);
            List<ProductCart> productCarts = cartService.getProductCartByCartId(user.getCart().getCartId());
            ProductCart productCart1 = new ProductCart();
            if (productCarts.isEmpty()) {
                productCart1 = cartService.saveNewProductCart(productCart);
            }
            for (int i = 0; i < productCarts.size(); i++) {
                if(Objects.equals(productCart.getProduct().getProductId(), productCarts.get(i).getProduct().getProductId()) &&
                        productCart.getSize().equals(productCarts.get(i).getSize())) {
                    productCarts.get(i).setQuantity(productCarts.get(i).getQuantity() + productCart.getQuantity());
                    productCarts.get(i).setProductPrice(productCarts.get(i).getProductPrice() + productCart.getProductPrice());
                    productCart1 = cartService.saveNewProductCart(productCarts.get(i));
                } else if (productCart.getProduct().getProductId() == productCarts.get(i).getProduct().getProductId()
                         && i == productCarts.size()-1) {
                    if(productCart.getSize().equals(productCarts.get(i).getSize()) != false) {
                        productCart1 = cartService.saveNewProductCart(productCart);
                    }
                } else if (productCart.getProduct().getProductId() != productCarts.get(i).getProduct().getProductId()
                        && i == productCarts.size()-1) {
                    productCart1 = cartService.saveNewProductCart(productCart);
                }
            }
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
