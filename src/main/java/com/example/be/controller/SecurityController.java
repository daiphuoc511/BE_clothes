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
            if(fate.equals("Kim")){
                List<Product> products;
                if(user.getGender() == 0 || user.getGender() == 1){
                    products = productService.getProductByColorAndClothesType("trang", "vang", "xam", "", user.getGender(), 2);
                } else {
                    products = productService.getProductByColorAndClothesType("trang", "vang", "xam", "", user.getGender(), user.getGender());
                }
                return new ResponseEntity<>(products, HttpStatus.OK);
            } else if(fate.equals("Moc")) {
                List<Product> products;
                if(user.getGender() == 0 || user.getGender() == 1){
                    products = productService.getProductByColorAndClothesType("xanh la", "nau", "", "", user.getGender(), 2);
                } else {
                    products = productService.getProductByColorAndClothesType("xanh la", "nau", "", "", user.getGender(), user.getGender());
                }
                return new ResponseEntity<>(products, HttpStatus.OK);
            } else if(fate.equals("Thuy")) {
                List<Product> products;
                if(user.getGender() == 0 || user.getGender() == 1){
                    products = productService.getProductByColorAndClothesType("xanh bien", "den", "", "", user.getGender(), 2);
                } else {
                    products = productService.getProductByColorAndClothesType("xanh bien", "den", "", "", user.getGender(), user.getGender());
                }
                return new ResponseEntity<>(products, HttpStatus.OK);
            } else if(fate.equals("Hoa")) {
                List<Product> products;
                if(user.getGender() == 0 || user.getGender() == 1) {
                    products = productService.getProductByColorAndClothesType("do", "cam", "vang", "hong", user.getGender(), 2);
                } else {
                    products = productService.getProductByColorAndClothesType("do", "cam", "vang", "hong", user.getGender(), user.getGender());
                }
                return new ResponseEntity<>(products, HttpStatus.OK);
            } else if(fate.equals("Tho")){
                List<Product> products;
                if(user.getGender() == 0 || user.getGender() == 1) {
                    products = productService.getProductByColorAndClothesType("nau", "vang kem", "", "", user.getGender(), 2);
                } else {
                    products = productService.getProductByColorAndClothesType("nau", "vang kem", "", "", user.getGender(), user.getGender());
                }
                return new ResponseEntity<>(products, HttpStatus.OK);
            } else {
                List<Product> products = productService.getProductByColorAndClothesType("", "", "", "", user.getGender(), user.getGender());
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
                } else {
                    if (((Integer.parseInt(user.getHeight()) >= 150 && Integer.parseInt(user.getHeight()) <= 160) &&
                            (Integer.parseInt(user.getWeight()) >= 40 && Integer.parseInt(user.getWeight()) < 45))
                        || ((Integer.parseInt(user.getHeight()) >= 145 && Integer.parseInt(user.getHeight()) < 150) &&
                            (Integer.parseInt(user.getWeight()) >= 45 && Integer.parseInt(user.getWeight()) <= 50))) {
                        if (user.getGender() == 0 || user.getGender() == 1)
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
