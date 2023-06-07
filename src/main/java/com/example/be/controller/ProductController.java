package com.example.be.controller;

import com.example.be.entity.Product;
import com.example.be.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/user")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/product_season")
    public ResponseEntity<List<Product>> getProductBySeason() {
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
            List<Product> products = productService.getProductByClothesType(0, 2);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product_male")
    public ResponseEntity<List<Product>> getProductByClothesTypeMale() {
        try {
            List<Product> products = productService.getProductByClothesType(1, 2);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product_man_jacket")
    public ResponseEntity<List<Product>> getProductByCategoryManJacket() {
        try {
            List<Product> products = productService.getProductByCategory_CategoryName("ao khoac nam");
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product_man_kaki")
    public ResponseEntity<List<Product>> getProductByCategoryMankaki() {
        try {
            List<Product> products = productService.getProductByCategory_CategoryName("quan kaki nam");
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product_man_polo")
    public ResponseEntity<List<Product>> getProductByCategoryManPolo() {
        try {
            List<Product> products = productService.getProductByCategory_CategoryName("ao polo nam");
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product_man_shirt")
    public ResponseEntity<List<Product>> getProductByCategoryManShirt() {
        try {
            List<Product> products = productService.getProductByCategory_CategoryName("ao so mi nam");
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product_man_tshirt")
    public ResponseEntity<List<Product>> getProductByCategoryManTshirt() {
        try {
            List<Product> products = productService.getProductByCategory_CategoryName("ao thun nam");
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product_man_trouser")
    public ResponseEntity<List<Product>> getProductByCategoryManTrouser() {
        try {
            List<Product> products = productService.getProductByCategory_CategoryName("quan tay nam");
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product_woman_dress")
    public ResponseEntity<List<Product>> getProductByCategoryWomanDress() {
        try {
            List<Product> products = productService.getProductByCategory_CategoryName("dam nu");
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product_woman_jacket")
    public ResponseEntity<List<Product>> getProductByCategoryWomanJacket() {
        try {
            List<Product> products = productService.getProductByCategory_CategoryName("ao khoac nu");
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product_woman_kaki")
    public ResponseEntity<List<Product>> getProductByCategoryWomanKaki() {
        try {
            List<Product> products = productService.getProductByCategory_CategoryName("quan kaki nu");
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product_woman_skirt")
    public ResponseEntity<List<Product>> getProductByCategoryWomanSkirt() {
        try {
            List<Product> products = productService.getProductByCategory_CategoryName("chan vay nu");
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product_woman_tshirt")
    public ResponseEntity<List<Product>> getProductByCategoryWomanTshirt() {
        try {
            List<Product> products = productService.getProductByCategory_CategoryName("ao thun nu");
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/update_size_product/{id}")
    public ResponseEntity<?> updateProductByProductId(@PathVariable("id") Integer id, Integer s, Integer m, Integer l, Integer xl, Integer xxl, Integer xxxl) {
        try {
            Product product = productService.updateProductByProductId(s, m, l, xl, xxl, xxxl, id);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
