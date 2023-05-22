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
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cart_id")
    private Integer cartId;

    @Column(name = "create_date", columnDefinition = "DATE")
    private String createDate;

    @Column(name = "total_cart")
    private Float totalCart;

    @OneToOne(mappedBy = "cart")
    @JsonIgnore
    private User user;

    @OneToOne(mappedBy = "cart")
    @JsonIgnore
    private Order order;

    @OneToMany(mappedBy = "cart")
    @JsonBackReference
    private Set<ProductCart> productCarts;
}
