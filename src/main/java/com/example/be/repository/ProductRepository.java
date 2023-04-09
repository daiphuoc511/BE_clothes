package com.example.be.repository;

import com.example.be.entity.Product;
import com.example.be.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<User, Integer> {

    @Query(value = "select * from product", nativeQuery = true)
    List<Product> getAllProduct();
}
