package com.example.be.controller;

import com.example.be.entity.Product;
import com.example.be.entity.User;
import com.example.be.entity.dto.JwtResponse;
import com.example.be.entity.dto.UserDTO;
import com.example.be.jwt.JwtService;
import com.example.be.payload.request.AuthenticationRequest;
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
                List<Product> products = productService.getProductByColorAndClothesType("trang", "vang", "xam", "", user.getGender());
                return new ResponseEntity<>(products, HttpStatus.OK);
            } else if(fate.equals("Moc")) {
                List<Product> products = productService.getProductByColorAndClothesType("xanh la", "nau", "", "", user.getGender());
                return new ResponseEntity<>(products, HttpStatus.OK);
            } else if(fate.equals("Thuy")) {
                List<Product> products = productService.getProductByColorAndClothesType("xanh bien", "den", "", "", user.getGender());
                return new ResponseEntity<>(products, HttpStatus.OK);
            } else if(fate.equals("Hoa")) {
                List<Product> products = productService.getProductByColorAndClothesType("do", "cam", "vang", "hong", user.getGender());
                return new ResponseEntity<>(products, HttpStatus.OK);
            } else if(fate.equals("Tho")){
                List<Product> products = productService.getProductByColorAndClothesType("nau", "vang kem", "", "", user.getGender());
                return new ResponseEntity<>(products, HttpStatus.OK);
            } else {
                List<Product> products = productService.getProductByColorAndClothesType("", "", "", "", user.getGender());
                return new ResponseEntity<>(products, HttpStatus.OK);
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
