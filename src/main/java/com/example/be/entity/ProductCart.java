package com.example.be.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_cart")
public class ProductCart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_cart_id")
    private Integer productCartId;

    @Column(name = "size", columnDefinition = "VARCHAR(10)")
    private String size;

    @Column(name = "quantity", columnDefinition = "VARCHAR(10)")
    private String quantity;

    @Column(name = "product_price")
    private Integer productPrice;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
