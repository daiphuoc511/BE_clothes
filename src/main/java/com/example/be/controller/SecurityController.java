package com.example.be.controller;

import com.example.be.entity.dto.JwtResponse;
import com.example.be.entity.dto.UserDTO;
import com.example.be.jwt.JwtService;
import com.example.be.payload.request.AuthenticationRequest;
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

import java.util.Arrays;
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
    PasswordEncoder encoder;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest){
        Authentication authentication =authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );
        System.out.println("HAHAHAAH");
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt=jwtService.generateTokenLogin(authentication);
        UserDetails userDetails=(UserDetails) authentication.getPrincipal();
        UserDTO user = userService.loadUserDetailByUserName(userDetails.getUsername());
        return new ResponseEntity<>(new JwtResponse(jwt,user,userDetails.getAuthorities()), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody ObjectNode json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        UserDTO user = userService.loadUserDetailByUserName(json.get("username").asText());
        if(user!= null) throw new Exception();
        String username=json.get("username").asText();
        String password=encoder.encode(json.get("password").asText());
        List<String> roles= Arrays.asList(mapper.convertValue( json.get("roles"), String[].class));
        UserDTO newUserDetail=mapper.convertValue(json.get("userDetail"),UserDTO.class);
        UserDTO newUserAccout = new UserDTO(username,password,roles);
        userService.saveNewUser(newUserDetail);
        return new ResponseEntity<>(newUserDetail,HttpStatus.OK);

    }

}
