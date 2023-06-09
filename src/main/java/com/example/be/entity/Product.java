package com.example.be.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "product_name", columnDefinition = "varchar(50)")
    private String productName;

    @Column(name = "image", columnDefinition = "VARCHAR(255)")
    private String image;

    @Column(name = "price")
    private Integer price;

    @Column(name = "brand", columnDefinition = "VARCHAR(255)")
    private String brand;

    @Column(name = "color", columnDefinition = "VARCHAR(255)")
    private String color;

    @Column(name = "s")
    private Integer s;

    @Column(name = "m")
    private Integer m;

    @Column(name = "l")
    private Integer l;

    @Column(name = "xl")
    private Integer xl;

    @Column(name = "xxl")
    private Integer xxl;

    @Column(name = "xxxl")
    private Integer xxxl;

    @Column(name = "clothes_type", columnDefinition = "int")
    private Integer clothesType;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "product")
    @JsonBackReference
    private Set<ProductCart> productCarts;

    @ManyToOne()
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private Category category;
}
