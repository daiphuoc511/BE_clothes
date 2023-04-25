package com.example.be.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;


@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @NotNull(message = "Id không được để trống!")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer userId;

    @NotBlank(message = "Họ tên không được để trống!")
    @Column(name = "name", columnDefinition = "varchar(50)")
    private String name;

    @Column(name = "username", columnDefinition = "VARCHAR(50) UNIQUE NOT NULL")
    private String username;

    @Column(name = "`password`", columnDefinition = "VARCHAR(255)")
    private String password;

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

    @Column(name = "email", columnDefinition = "VARCHAR(50) UNIQUE NOT NULL")
    private String email;

    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "roleId"))
    private Set<Role> roles = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id", referencedColumnName = "cart_id")
    private Cart cart;

    public User(Integer userId, String name, String username, String password, String avatar, String birthday, Integer gender, String fate, Float height, Float weight, String email, Set<Role> roles, Cart cart) {
        this.userId = userId;
        this.name = name;
        this.username = username;
        this.password = password;
        this.avatar = avatar;
        this.birthday = birthday;
        this.gender = gender;
        this.fate = fate;
        this.height = height;
        this.weight = weight;
        this.email = email;
        this.roles = roles;
        this.cart = cart;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        roles.stream().forEach(i -> authorities.add(new SimpleGrantedAuthority(i.getRoleName())));
        return List.of(new SimpleGrantedAuthority(authorities.toString()));
    }

    @Override
    public String getPassword() {
        return email;
    }

    @Override
    public String getUsername() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
