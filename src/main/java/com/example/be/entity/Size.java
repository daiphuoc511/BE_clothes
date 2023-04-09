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
@Table(name = "size")
public class Size {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "size_id")
    private Integer sizeId;

    @Column(name = "size_name", columnDefinition = "varchar(10)")
    private String sizeName;

    @Column(name = "size_description", columnDefinition = "varchar(255)")
    private String sizeDescription;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
