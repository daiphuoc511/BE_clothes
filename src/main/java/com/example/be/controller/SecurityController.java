package com.example.be.controller;

import com.example.be.entity.Product;
import com.example.be.entity.ProductCart;
import com.example.be.entity.User;
import com.example.be.entity.dto.JwtResponse;
import com.example.be.entity.dto.UserDTO;
import com.example.be.jwt.JwtService;
import com.example.be.payload.request.AuthenticationRequest;
import com.example.be.service.CartService;
import com.example.be.service.ProductService;
import com.example.be.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class SecurityController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    JwtService jwtService;

    @Autowired
    ProductService productService;

    @Autowired
    CartService cartService;

    @Autowired
    PasswordEncoder encoder;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest){
        try {
            Authentication authentication =authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getEmail(),
                            authenticationRequest.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt=jwtService.generateTokenLogin(authentication);
            UserDetails userDetails=(UserDetails) authentication.getPrincipal();
            UserDTO user = userService.loadUserDetailByUserName(userDetails.getUsername());
            return new ResponseEntity<>(new JwtResponse(jwt,user,userDetails.getAuthorities()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO) throws Exception {
        try {
            User user = userService.saveNewUser(userDTO);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product_user")
    public ResponseEntity<List<Product>> getProductByUser(Authentication authentication) {
        String username = authentication.getName();
        try {
            User user = userService.findByUser(username);
            String fate = user.getFate();
            LocalDate localDate = LocalDate.now();
            String month = localDate.getMonth().toString();
            if(fate.equals("Kim")){
                List<Product> products;
                if (month == "JANUARY" || month == "FEBRUARY" || month == "MARCH") {
                    products = productService.getProductByColorAndClothesType("trang", "vang", "xam", "", "hong nhat", "xanh bien nhat", "vang kem", "trang kem", user.getGender(), 2);
                } else if (month == "APRIL" || month == "MAY" || month == "JUNE") {
                    products = productService.getProductByColorAndClothesType("trang", "vang", "xam", "", "hong nhat", "xanh bien", "", "cam", user.getGender(), 2);
                } else if (month == "JULY" || month == "AUGUST" || month == "SEPTEMBER") {
                    products = productService.getProductByColorAndClothesType("trang", "vang", "xam", "", "nau", "cam", "vang kem", "do", user.getGender(), 2);
                } else {
                    products = productService.getProductByColorAndClothesType("trang", "vang", "xam", "", "den", "", "", "xanh la", user.getGender(), 2);
                }
                return new ResponseEntity<>(products, HttpStatus.OK);
            } else if(fate.equals("Moc")) {
                List<Product> products;
                if (month == "JANUARY" || month == "FEBRUARY" || month == "MARCH") {
                    products = productService.getProductByColorAndClothesType("xanh la", "nau", "", "", "hong nhat", "xanh bien nhat", "vang kem", "trang kem", user.getGender(), 2);
                } else if (month == "APRIL" || month == "MAY" || month == "JUNE") {
                    products = productService.getProductByColorAndClothesType("xanh la", "nau", "", "", "hong nhat", "xanh bien", "", "cam", user.getGender(), 2);
                } else if (month == "JULY" || month == "AUGUST" || month == "SEPTEMBER") {
                    products = productService.getProductByColorAndClothesType("xanh la", "nau", "", "", "", "cam", "vang kem", "do", user.getGender(), 2);
                } else {
                    products = productService.getProductByColorAndClothesType("xanh la", "nau", "", "", "den", "", "", "", user.getGender(), 2);
                }
                return new ResponseEntity<>(products, HttpStatus.OK);
            } else if(fate.equals("Thuy")) {
                List<Product> products;
                if (month == "JANUARY" || month == "FEBRUARY" || month == "MARCH") {
                    products = productService.getProductByColorAndClothesType("xanh bien", "den", "", "", "hong nhat", "xanh bien nhat", "vang kem", "trang kem", user.getGender(), 2);
                } else if (month == "APRIL" || month == "MAY" || month == "JUNE") {
                    products = productService.getProductByColorAndClothesType("xanh bien", "den", "", "", "hong nhat", "", "", "cam", user.getGender(), 2);
                } else if (month == "JULY" || month == "AUGUST" || month == "SEPTEMBER") {
                    products = productService.getProductByColorAndClothesType("xanh bien", "den", "", "", "nau", "cam", "vang kem", "do", user.getGender(), 2);
                } else {
                    products = productService.getProductByColorAndClothesType("xanh bien", "den", "", "", "", "", "", "xanh la", user.getGender(), 2);
                }
                return new ResponseEntity<>(products, HttpStatus.OK);
            } else if(fate.equals("Hoa")) {
                List<Product> products;
                if (month == "JANUARY" || month == "FEBRUARY" || month == "MARCH") {
                    products = productService.getProductByColorAndClothesType("do", "cam", "vang", "hong", "hong nhat", "xanh bien nhat", "vang kem", "trang kem", user.getGender(), 2);
                } else if (month == "APRIL" || month == "MAY" || month == "JUNE") {
                    products = productService.getProductByColorAndClothesType("do", "cam", "vang", "hong", "hong nhat", "xanh bien", "", "", user.getGender(), 2);
                } else if (month == "JULY" || month == "AUGUST" || month == "SEPTEMBER") {
                    products = productService.getProductByColorAndClothesType("do", "cam", "vang", "hong", "nau", "", "vang kem", "", user.getGender(), 2);
                } else {
                    products = productService.getProductByColorAndClothesType("do", "cam", "vang", "hong", "den", "", "", "xanh la", user.getGender(), 2);
                }
                return new ResponseEntity<>(products, HttpStatus.OK);
            } else if(fate.equals("Tho")){
                List<Product> products;
                if (month == "JANUARY" || month == "FEBRUARY" || month == "MARCH") {
                    products = productService.getProductByColorAndClothesType("nau", "vang kem", "", "", "hong nhat", "xanh bien nhat", "", "trang kem", user.getGender(), 2);
                } else if (month == "APRIL" || month == "MAY" || month == "JUNE") {
                    products = productService.getProductByColorAndClothesType("nau", "vang kem", "", "", "hong nhat", "xanh bien", "", "cam", user.getGender(), 2);
                } else if (month == "JULY" || month == "AUGUST" || month == "SEPTEMBER") {
                    products = productService.getProductByColorAndClothesType("nau", "vang kem", "", "", "", "cam", "", "do", user.getGender(), 2);
                } else {
                    products = productService.getProductByColorAndClothesType("nau", "vang kem", "", "", "den", "", "", "xanh la", user.getGender(), 2);
                }
                return new ResponseEntity<>(products, HttpStatus.OK);
            } else {
                List<Product> products = productService.getProductByColorAndClothesType("", "", "", "","", "", "", "", user.getGender(), 2);
                return new ResponseEntity<>(products, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product_size_user")
    public ResponseEntity<List<Product>> getProductSizeByUser(Authentication authentication) {
        String username = authentication.getName();
        try {
            User user = userService.findByUser(username);
            List<Product> products = new ArrayList<>();
            List<ProductCart> productCarts = cartService.getProductCartByCartId(user.getCart().getCartId());
            List<Product> products1 = new ArrayList<>();
            if (productCarts == null || productCarts.isEmpty()) {
                if((user.getHeight() == null && user.getWeight() == null) || (user.getHeight().isEmpty() && user.getWeight().isEmpty())
                        || (user.getHeight() == null && user.getWeight().isEmpty()) || (user.getHeight().isEmpty() && user.getWeight() == null)) {
                    return new ResponseEntity<>(HttpStatus.OK);
                } else if (user.getHeight() == null || user.getHeight().isEmpty()) {
                    if (Integer.parseInt(user.getWeight()) >= 40 && Integer.parseInt(user.getWeight()) < 55) {
                        products = productService.getProductByClothesTypeAndSGreaterThan(user.getGender(), 2, 0);
                    } else if (Integer.parseInt(user.getWeight()) >= 50 && Integer.parseInt(user.getWeight()) <= 60) {
                        products = productService.getProductByClothesTypeAndMGreaterThan(user.getGender(), 2, 0);
                    } else if (Integer.parseInt(user.getWeight()) >= 55 && Integer.parseInt(user.getWeight()) <= 65) {
                        products = productService.getProductByClothesTypeAndLGreaterThan(user.getGender(), 2, 0);
                    } else if (Integer.parseInt(user.getWeight()) >= 60 && Integer.parseInt(user.getWeight()) <= 70) {
                        products = productService.getProductByClothesTypeAndXlGreaterThan(user.getGender(), 2, 0);
                    } else if (Integer.parseInt(user.getWeight()) >= 65 && Integer.parseInt(user.getWeight()) <= 75) {
                        products = productService.getProductByClothesTypeAndXxlGreaterThan(user.getGender(), 2, 0);
                    } else if (Integer.parseInt(user.getWeight()) >= 70 && Integer.parseInt(user.getWeight()) <= 80) {
                        products = productService.getProductByClothesTypeAndXxxlGreaterThan(user.getGender(), 2, 0);
                    }
                    return new ResponseEntity<>(products, HttpStatus.OK);
                } else if (user.getWeight() == null || user.getWeight().isEmpty()) {
                    if (Integer.parseInt(user.getHeight()) >= 145 && Integer.parseInt(user.getHeight()) <= 160) {
                        products = productService.getProductByClothesTypeAndSGreaterThan(user.getGender(), 2, 0);
                    } else if (Integer.parseInt(user.getHeight()) >= 155 && Integer.parseInt(user.getHeight()) <= 165) {
                        products = productService.getProductByClothesTypeAndMGreaterThan(user.getGender(), 2, 0);
                    } else if (Integer.parseInt(user.getHeight()) >= 160 && Integer.parseInt(user.getHeight()) <= 170) {
                        products = productService.getProductByClothesTypeAndLGreaterThan(user.getGender(), 2, 0);
                    } else if (Integer.parseInt(user.getHeight()) >= 165 && Integer.parseInt(user.getHeight()) <= 175) {
                        products = productService.getProductByClothesTypeAndXlGreaterThan(user.getGender(), 2, 0);
                    } else if (Integer.parseInt(user.getHeight()) >= 170 && Integer.parseInt(user.getHeight()) <= 180) {
                        products = productService.getProductByClothesTypeAndXxlGreaterThan(user.getGender(), 2, 0);
                    } else if (Integer.parseInt(user.getHeight()) >= 175 && Integer.parseInt(user.getHeight()) <= 185) {
                        products = productService.getProductByClothesTypeAndXxxlGreaterThan(user.getGender(), 2, 0);
                    }
                    return new ResponseEntity<>(products, HttpStatus.OK);
                } else {
                    if (((Integer.parseInt(user.getHeight()) >= 150 && Integer.parseInt(user.getHeight()) <= 160) &&
                            (Integer.parseInt(user.getWeight()) >= 40 && Integer.parseInt(user.getWeight()) < 45))
                            || ((Integer.parseInt(user.getHeight()) >= 145 && Integer.parseInt(user.getHeight()) < 150) &&
                            (Integer.parseInt(user.getWeight()) >= 45 && Integer.parseInt(user.getWeight()) <= 50))) {
                            products = productService.getProductByClothesTypeAndSGreaterThan(user.getGender(), 2, 0);
                    } else if ((Integer.parseInt(user.getHeight()) >= 150 && Integer.parseInt(user.getHeight()) <= 160) &&
                            (Integer.parseInt(user.getWeight()) >= 45 && Integer.parseInt(user.getWeight()) <= 55)) {
                        if (Integer.parseInt(user.getHeight()) > 155 && Integer.parseInt(user.getWeight()) > 50) {
                            products = productService.getProductByClothesTypeAndMGreaterThan(user.getGender(), 2, 0);
                        } else {
                            products = productService.getProductByClothesTypeAndSGreaterThan(user.getGender(), 2, 0);
                        }
                    } else if ((Integer.parseInt(user.getHeight()) >= 155 && Integer.parseInt(user.getHeight()) <= 165) &&
                            (Integer.parseInt(user.getWeight()) >= 50 && Integer.parseInt(user.getWeight()) <= 60)) {
                        if (Integer.parseInt(user.getHeight()) > 160 && Integer.parseInt(user.getWeight()) > 55) {
                            products = productService.getProductByClothesTypeAndLGreaterThan(user.getGender(), 2, 0);
                        } else {
                            products = productService.getProductByClothesTypeAndMGreaterThan(user.getGender(), 2, 0);
                        }
                    } else if ((Integer.parseInt(user.getHeight()) >= 160 && Integer.parseInt(user.getHeight()) <= 170) &&
                            (Integer.parseInt(user.getWeight()) >= 55 && Integer.parseInt(user.getWeight()) <= 65)) {
                        if (Integer.parseInt(user.getHeight()) > 165 && Integer.parseInt(user.getWeight()) > 60) {
                            products = productService.getProductByClothesTypeAndXlGreaterThan(user.getGender(), 2, 0);
                        } else {
                            products = productService.getProductByClothesTypeAndLGreaterThan(user.getGender(), 2, 0);
                        }
                    } else if ((Integer.parseInt(user.getHeight()) >= 165 && Integer.parseInt(user.getHeight()) <= 175) &&
                            (Integer.parseInt(user.getWeight()) >= 60 && Integer.parseInt(user.getWeight()) <= 70)) {
                        if (Integer.parseInt(user.getHeight()) > 170 && Integer.parseInt(user.getWeight()) > 65) {
                            products = productService.getProductByClothesTypeAndXxlGreaterThan(user.getGender(), 2, 0);
                        } else {
                            products = productService.getProductByClothesTypeAndXlGreaterThan(user.getGender(), 2, 0);
                        }
                    } else if ((Integer.parseInt(user.getHeight()) >= 170 && Integer.parseInt(user.getHeight()) <= 180) &&
                            (Integer.parseInt(user.getWeight()) >= 65 && Integer.parseInt(user.getWeight()) <= 75)) {
                        if (Integer.parseInt(user.getHeight()) > 175 && Integer.parseInt(user.getWeight()) > 70) {
                            products = productService.getProductByClothesTypeAndXxxlGreaterThan(user.getGender(), 2, 0);
                        } else {
                            products = productService.getProductByClothesTypeAndXxlGreaterThan(user.getGender(), 2, 0);
                        }
                    } else if ((Integer.parseInt(user.getHeight()) >= 175 && Integer.parseInt(user.getHeight()) <= 185) &&
                            (Integer.parseInt(user.getWeight()) >= 70 && Integer.parseInt(user.getWeight()) <= 80)) {
                        products = productService.getProductByClothesTypeAndXxxlGreaterThan(user.getGender(), 2, 0);
                    }
                    return new ResponseEntity<>(products, HttpStatus.OK);
                }
            } else {
                if((user.getHeight() == null && user.getWeight() == null) || (user.getHeight().isEmpty() && user.getWeight().isEmpty())
                        || (user.getHeight() == null && user.getWeight().isEmpty()) || (user.getHeight().isEmpty() && user.getWeight() == null)) {
                    for (int i = 0; i < productCarts.size(); i++) {
                        if (productCarts.get(i).getSize().equals("S")) {
                            products1.addAll(productService.getProductBySGreaterThanAndCategory_CategoryName(0, productCarts.get(i).getProduct().getCategory().getCategoryName()));
                        } else if (productCarts.get(i).getSize().equals("M")) {
                            products1.addAll(productService.getProductByMGreaterThanAndCategory_CategoryName(0, productCarts.get(i).getProduct().getCategory().getCategoryName()));
                        } else if (productCarts.get(i).getSize().equals("L")) {
                            products1.addAll(productService.getProductByLGreaterThanAndCategory_CategoryName(0, productCarts.get(i).getProduct().getCategory().getCategoryName()));
                        } else if (productCarts.get(i).getSize().equals("XL")) {
                            products1.addAll(productService.getProductByXlGreaterThanAndCategory_CategoryName(0, productCarts.get(i).getProduct().getCategory().getCategoryName()));
                        } else if (productCarts.get(i).getSize().equals("XXL")) {
                            products1.addAll(productService.getProductByXxlGreaterThanAndCategory_CategoryName(0, productCarts.get(i).getProduct().getCategory().getCategoryName()));
                        } else if (productCarts.get(i).getSize().equals("XXXL")) {
                            products1.addAll(productService.getProductByXxxlGreaterThanAndCategory_CategoryName(0, productCarts.get(i).getProduct().getCategory().getCategoryName()));
                        }
                    }
                    return new ResponseEntity<>(products1, HttpStatus.OK);
                } else if (user.getHeight() == null || user.getHeight().isEmpty()) {
                    if (Integer.parseInt(user.getWeight()) >= 40 && Integer.parseInt(user.getWeight()) < 55) {
                        products1.addAll(productService.getProductByClothesTypeAndSGreaterThan(user.getGender(), 2, 0));
                    } else if (Integer.parseInt(user.getWeight()) >= 50 && Integer.parseInt(user.getWeight()) <= 60) {
                        products1.addAll(productService.getProductByClothesTypeAndMGreaterThan(user.getGender(), 2, 0));
                    } else if (Integer.parseInt(user.getWeight()) >= 55 && Integer.parseInt(user.getWeight()) <= 65) {
                        products1.addAll(productService.getProductByClothesTypeAndLGreaterThan(user.getGender(), 2, 0));
                    } else if (Integer.parseInt(user.getWeight()) >= 60 && Integer.parseInt(user.getWeight()) <= 70) {
                        products1.addAll(productService.getProductByClothesTypeAndXlGreaterThan(user.getGender(), 2, 0));
                    } else if (Integer.parseInt(user.getWeight()) >= 65 && Integer.parseInt(user.getWeight()) <= 75) {
                        products1.addAll(productService.getProductByClothesTypeAndXxlGreaterThan(user.getGender(), 2, 0));
                    } else if (Integer.parseInt(user.getWeight()) >= 70 && Integer.parseInt(user.getWeight()) <= 80) {
                        products1.addAll(productService.getProductByClothesTypeAndXxxlGreaterThan(user.getGender(), 2, 0));
                    }
                    for (int i = 0; i < productCarts.size(); i++) {
                        if (productCarts.get(i).getSize().equals("S")) {
                            products1.addAll(productService.getProductBySGreaterThanAndCategory_CategoryName(0, productCarts.get(i).getProduct().getCategory().getCategoryName()));
                        } else if (productCarts.get(i).getSize().equals("M")) {
                            products1.addAll(productService.getProductByMGreaterThanAndCategory_CategoryName(0, productCarts.get(i).getProduct().getCategory().getCategoryName()));
                        } else if (productCarts.get(i).getSize().equals("L")) {
                            products1.addAll(productService.getProductByLGreaterThanAndCategory_CategoryName(0, productCarts.get(i).getProduct().getCategory().getCategoryName()));
                        } else if (productCarts.get(i).getSize().equals("XL")) {
                            products1.addAll(productService.getProductByXlGreaterThanAndCategory_CategoryName(0, productCarts.get(i).getProduct().getCategory().getCategoryName()));
                        } else if (productCarts.get(i).getSize().equals("XXL")) {
                            products1.addAll(productService.getProductByXxlGreaterThanAndCategory_CategoryName(0, productCarts.get(i).getProduct().getCategory().getCategoryName()));
                        } else if (productCarts.get(i).getSize().equals("XXXL")) {
                            products1.addAll(productService.getProductByXxxlGreaterThanAndCategory_CategoryName(0, productCarts.get(i).getProduct().getCategory().getCategoryName()));
                        }
                    }
                } else if (user.getWeight() == null || user.getWeight().isEmpty()) {
                    if (Integer.parseInt(user.getHeight()) >= 145 && Integer.parseInt(user.getHeight()) <= 160) {
                        products1.addAll(productService.getProductByClothesTypeAndSGreaterThan(user.getGender(), 2, 0));
                    } else if (Integer.parseInt(user.getHeight()) >= 155 && Integer.parseInt(user.getHeight()) <= 165) {
                        products1.addAll(productService.getProductByClothesTypeAndMGreaterThan(user.getGender(), 2, 0));
                    } else if (Integer.parseInt(user.getHeight()) >= 160 && Integer.parseInt(user.getHeight()) <= 170) {
                        products1.addAll(productService.getProductByClothesTypeAndLGreaterThan(user.getGender(), 2, 0));
                    } else if (Integer.parseInt(user.getHeight()) >= 165 && Integer.parseInt(user.getHeight()) <= 175) {
                        products1.addAll(productService.getProductByClothesTypeAndXlGreaterThan(user.getGender(), 2, 0));
                    } else if (Integer.parseInt(user.getHeight()) >= 170 && Integer.parseInt(user.getHeight()) <= 180) {
                        products1.addAll(productService.getProductByClothesTypeAndXxlGreaterThan(user.getGender(), 2, 0));
                    } else if (Integer.parseInt(user.getHeight()) >= 175 && Integer.parseInt(user.getHeight()) <= 185) {
                        products1.addAll(productService.getProductByClothesTypeAndXxxlGreaterThan(user.getGender(), 2, 0));
                    }
                    for (int i = 0; i < productCarts.size(); i++) {
                        if (productCarts.get(i).getSize().equals("S")) {
                            products1.addAll(productService.getProductBySGreaterThanAndCategory_CategoryName(0, productCarts.get(i).getProduct().getCategory().getCategoryName()));
                        } else if (productCarts.get(i).getSize().equals("M")) {
                            products1.addAll(productService.getProductByMGreaterThanAndCategory_CategoryName(0, productCarts.get(i).getProduct().getCategory().getCategoryName()));
                        } else if (productCarts.get(i).getSize().equals("L")) {
                            products1.addAll(productService.getProductByLGreaterThanAndCategory_CategoryName(0, productCarts.get(i).getProduct().getCategory().getCategoryName()));
                        } else if (productCarts.get(i).getSize().equals("XL")) {
                            products1.addAll(productService.getProductByXlGreaterThanAndCategory_CategoryName(0, productCarts.get(i).getProduct().getCategory().getCategoryName()));
                        } else if (productCarts.get(i).getSize().equals("XXL")) {
                            products1.addAll(productService.getProductByXxlGreaterThanAndCategory_CategoryName(0, productCarts.get(i).getProduct().getCategory().getCategoryName()));
                        } else if (productCarts.get(i).getSize().equals("XXXL")) {
                            products1.addAll(productService.getProductByXxxlGreaterThanAndCategory_CategoryName(0, productCarts.get(i).getProduct().getCategory().getCategoryName()));
                        }
                    }
                } else {
                    if (((Integer.parseInt(user.getHeight()) >= 150 && Integer.parseInt(user.getHeight()) <= 160) &&
                            (Integer.parseInt(user.getWeight()) >= 40 && Integer.parseInt(user.getWeight()) < 45))
                            || ((Integer.parseInt(user.getHeight()) >= 145 && Integer.parseInt(user.getHeight()) < 150) &&
                            (Integer.parseInt(user.getWeight()) >= 45 && Integer.parseInt(user.getWeight()) <= 50))) {
                            products1.addAll(productService.getProductByClothesTypeAndSGreaterThan(user.getGender(), 2, 0));
                    } else if ((Integer.parseInt(user.getHeight()) >= 150 && Integer.parseInt(user.getHeight()) <= 160) &&
                            (Integer.parseInt(user.getWeight()) >= 45 && Integer.parseInt(user.getWeight()) <= 55)) {
                        if (Integer.parseInt(user.getHeight()) > 155 && Integer.parseInt(user.getWeight()) > 50) {
                            products1.addAll(productService.getProductByClothesTypeAndMGreaterThan(user.getGender(), 2, 0));
                        } else {
                            products1.addAll(productService.getProductByClothesTypeAndSGreaterThan(user.getGender(), 2, 0));
                        }
                    } else if ((Integer.parseInt(user.getHeight()) >= 155 && Integer.parseInt(user.getHeight()) <= 165) &&
                            (Integer.parseInt(user.getWeight()) >= 50 && Integer.parseInt(user.getWeight()) <= 60)) {
                        if (Integer.parseInt(user.getHeight()) > 160 && Integer.parseInt(user.getWeight()) > 55) {
                            products1.addAll(productService.getProductByClothesTypeAndLGreaterThan(user.getGender(), 2, 0));
                        } else {
                            products1.addAll(productService.getProductByClothesTypeAndMGreaterThan(user.getGender(), 2, 0));
                        }
                    } else if ((Integer.parseInt(user.getHeight()) >= 160 && Integer.parseInt(user.getHeight()) <= 170) &&
                            (Integer.parseInt(user.getWeight()) >= 55 && Integer.parseInt(user.getWeight()) <= 65)) {
                        if (Integer.parseInt(user.getHeight()) > 165 && Integer.parseInt(user.getWeight()) > 60) {
                            products1.addAll(productService.getProductByClothesTypeAndXlGreaterThan(user.getGender(), 2, 0));
                        } else {
                            products1.addAll(productService.getProductByClothesTypeAndLGreaterThan(user.getGender(), 2, 0));
                        }
                    } else if ((Integer.parseInt(user.getHeight()) >= 165 && Integer.parseInt(user.getHeight()) <= 175) &&
                            (Integer.parseInt(user.getWeight()) >= 60 && Integer.parseInt(user.getWeight()) <= 70)) {
                        if (Integer.parseInt(user.getHeight()) > 170 && Integer.parseInt(user.getWeight()) > 65) {
                            products1.addAll(productService.getProductByClothesTypeAndXxlGreaterThan(user.getGender(), 2, 0));
                        } else {
                            products1.addAll(productService.getProductByClothesTypeAndXlGreaterThan(user.getGender(), 2, 0));
                        }
                    } else if ((Integer.parseInt(user.getHeight()) >= 170 && Integer.parseInt(user.getHeight()) <= 180) &&
                            (Integer.parseInt(user.getWeight()) >= 65 && Integer.parseInt(user.getWeight()) <= 75)) {
                        if (Integer.parseInt(user.getHeight()) > 175 && Integer.parseInt(user.getWeight()) > 70) {
                            products1.addAll(productService.getProductByClothesTypeAndXxxlGreaterThan(user.getGender(), 2, 0));
                        } else {
                            products1.addAll(productService.getProductByClothesTypeAndXxlGreaterThan(user.getGender(), 2, 0));
                        }
                    } else if ((Integer.parseInt(user.getHeight()) >= 175 && Integer.parseInt(user.getHeight()) <= 185) &&
                            (Integer.parseInt(user.getWeight()) >= 70 && Integer.parseInt(user.getWeight()) <= 80)) {
                        products1.addAll(productService.getProductByClothesTypeAndXxxlGreaterThan(user.getGender(), 2, 0));
                    }
                    for (int i = 0; i < productCarts.size(); i++) {
                        if (productCarts.get(i).getSize().equals("S")) {
                            products1.addAll(productService.getProductBySGreaterThanAndCategory_CategoryName(0, productCarts.get(i).getProduct().getCategory().getCategoryName()));
                        } else if (productCarts.get(i).getSize().equals("M")) {
                            products1.addAll(productService.getProductByMGreaterThanAndCategory_CategoryName(0, productCarts.get(i).getProduct().getCategory().getCategoryName()));
                        } else if (productCarts.get(i).getSize().equals("L")) {
                            products1.addAll(productService.getProductByLGreaterThanAndCategory_CategoryName(0, productCarts.get(i).getProduct().getCategory().getCategoryName()));
                        } else if (productCarts.get(i).getSize().equals("XL")) {
                            products1.addAll(productService.getProductByXlGreaterThanAndCategory_CategoryName(0, productCarts.get(i).getProduct().getCategory().getCategoryName()));
                        } else if (productCarts.get(i).getSize().equals("XXL")) {
                            products1.addAll(productService.getProductByXxlGreaterThanAndCategory_CategoryName(0, productCarts.get(i).getProduct().getCategory().getCategoryName()));
                        } else if (productCarts.get(i).getSize().equals("XXXL")) {
                            products1.addAll(productService.getProductByXxxlGreaterThanAndCategory_CategoryName(0, productCarts.get(i).getProduct().getCategory().getCategoryName()));
                        }
                    }
                }
//                for (int i = 0; i < productCarts.size(); i++) {
//                    if (productCarts.get(i).getSize().equals("S")) {
//                        products1.addAll(productService.getProductBySGreaterThanAndCategory_CategoryName(0, productCarts.get(i).getProduct().getCategory().getCategoryName()));
//                    } else if (productCarts.get(i).getSize().equals("M")) {
//                        products1.addAll(productService.getProductByMGreaterThanAndCategory_CategoryName(0, productCarts.get(i).getProduct().getCategory().getCategoryName()));
//                    } else if (productCarts.get(i).getSize().equals("L")) {
//                        products1.addAll(productService.getProductByLGreaterThanAndCategory_CategoryName(0, productCarts.get(i).getProduct().getCategory().getCategoryName()));
//                    } else if (productCarts.get(i).getSize().equals("XL")) {
//                        products1.addAll(productService.getProductByXlGreaterThanAndCategory_CategoryName(0, productCarts.get(i).getProduct().getCategory().getCategoryName()));
//                    } else if (productCarts.get(i).getSize().equals("XXL")) {
//                        products1.addAll(productService.getProductByXxlGreaterThanAndCategory_CategoryName(0, productCarts.get(i).getProduct().getCategory().getCategoryName()));
//                    } else if (productCarts.get(i).getSize().equals("XXXL")) {
//                        products1.addAll(productService.getProductByXxxlGreaterThanAndCategory_CategoryName(0, productCarts.get(i).getProduct().getCategory().getCategoryName()));
//                    }
//                }
                HashSet<Product> products2 = new HashSet<>(products1);
                products1.clear();
                products1.addAll(products2);
                return new ResponseEntity<>(products1 ,HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/profile_user")
    public ResponseEntity<Object> getProfileUser(Authentication authentication) {
        String username = authentication.getName();
        try {
           User user1 = userService.findByUser(username);
            return new ResponseEntity<>(user1, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/edit_user/{userId}")
    public ResponseEntity<Object> updateProfileUser( @PathVariable(name = "userId") Integer userId, @RequestBody ObjectNode json) throws Exception {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.findAndRegisterModules();
            UserDTO user = userService.findUserById(userId);
            if(user== null) throw new Exception();
            UserDTO userDetail = mapper.convertValue(json.get("userDTO"), UserDTO.class);
            userService.updateUser(userId, userDetail);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
