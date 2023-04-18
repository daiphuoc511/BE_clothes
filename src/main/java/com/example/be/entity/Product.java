package com.example.be.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "Tên sản phẩm không được để trống!")
    @Column(name = "product_name", columnDefinition = "varchar(50)")
    private String productName;

    @NotBlank(message = "Ảnh sản phẩm không được để trống!")
    @Column(name = "image", columnDefinition = "VARCHAR(255)")
    private String image;

    @Column(name = "price")
    private Float price;

    @Column(name = "brand", columnDefinition = "VARCHAR(255)")
    private String brand;

    @Column(name = "color", columnDefinition = "VARCHAR(255)")
    private String color;

    @Column(name = "quantity")
    private Integer quantity;

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

    @Column(name = "clothes_type", columnDefinition = "bit(1)")
    private Boolean clothesType;

    @OneToMany(mappedBy = "product")
    @JsonBackReference
    private Set<ProductCart> roles;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;
}
