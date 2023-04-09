package com.example.be.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
}
