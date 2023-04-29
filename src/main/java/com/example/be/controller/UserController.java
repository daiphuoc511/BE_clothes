//package com.example.be.controller;
//
//import com.example.be.entity.User;
//import com.example.be.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("api/user")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
//public class UserController {
//
//    @Autowired
//    UserService userService;
//
//    @GetMapping("/all_user")
//    public ResponseEntity<List<User>> getAllUser() {
//        try {
//            List<User> users = userService.getAllUser();
//            return new ResponseEntity<>(users, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }
//}
