package com.example.be.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @NotNull(message = "Id không được để trống!")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @NotBlank(message = "Họ tên không được để trống!")
    @Column(name = "name", columnDefinition = "varchar(50)")
    private String name;

    @Column(name = "avatar", columnDefinition = "varchar(255)")
    private String avatar;

    @NotBlank(message = "Ngày sinh không được để trống!")
    @Column(name = "birthday", columnDefinition = "DATE")
    private String birthday;

    @NotNull(message = "Giới tính không được để trống!")
    @Column(name = "gender", columnDefinition = "int")
    private Integer gender;

    @Column(name = "fate", columnDefinition = "varchar(15)")
    private String fate;

    @Column(name = "height")
    private Float height;

    @Column(name = "weight")
    private Float weight;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    private Account account;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id", referencedColumnName = "cart_id")
    private Cart cart;

}
