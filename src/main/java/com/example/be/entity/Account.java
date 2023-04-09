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
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Integer accountId;

    @Column(name = "username", columnDefinition = "VARCHAR(50) UNIQUE NOT NULL")
    private String username;

    @Column(name = "`password`", columnDefinition = "VARCHAR(255)")
    private String password;

    @Column(name = "register_date", columnDefinition = "DATE")
    private String registerDate;

//    @Column(name = "account_status", columnDefinition = "BIT(1)")
//    private boolean accountStatus;

    @OneToOne(mappedBy = "account")
    @JsonIgnore
    private User user;

    @Column(name = "verification_code", columnDefinition = "varchar(100)")
    private String verificationCode;

    @Column(name = "is_enable", columnDefinition = "bit(1)")
    private Boolean isEnable;

    @OneToMany(mappedBy = "account")
    @JsonBackReference
    private Set<AccountRole> roles;
}

