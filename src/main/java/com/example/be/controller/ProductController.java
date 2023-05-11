package com.example.be.controller;

import com.example.be.entity.Product;
import com.example.be.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/user")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/all_product")
    public ResponseEntity<List<Product>> getAllProduct() {
        try {
            LocalDate localDate = LocalDate.now();
            String month = localDate.getMonth().toString();
            if (month == "JANUARY" || month == "FEBRUARY" || month == "MARCH") {
                List<Product> products = productService.getProductByColor("hong nhat", "xanh bien nhat", "vang kem", "trang kem", "", "");
                return new ResponseEntity<>(products, HttpStatus.OK);
            } else if (month == "APRIL" || month == "MAY" || month == "JUNE") {
                List<Product> products = productService.getProductByColor("hong nhat", "xanh bien", "vang", "cam", "", "");
                return new ResponseEntity<>(products, HttpStatus.OK);
            } else if (month == "JULY" || month == "AUGUST" || month == "SEPTEMBER") {
                List<Product> products = productService.getProductByColor("nau", "cam", "vang kem", "do", "", "");
                return new ResponseEntity<>(products, HttpStatus.OK);
            } else {
                List<Product> products = productService.getProductByColor("den", "trang", "xam", "xanh la", "", "");
                return new ResponseEntity<>(products, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product_female")
    public ResponseEntity<List<Product>> getProductByClothesTypeFemale() {
        try {
            List<Product> products = productService.getProductByClothesType(0);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product_male")
    public ResponseEntity<List<Product>> getProductByClothesTypeMale() {
        try {
            List<Product> products = productService.getProductByClothesType(1);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product_user")
    public ResponseEntity<List<Product>> getProductByUser() {
        try {
            List<Product> products = productService.getProductByColor("den", "", "", "", "", "");
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
