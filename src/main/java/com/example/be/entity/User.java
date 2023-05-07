package com.example.be.entity;

import com.example.be.entity.dto.UserDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @NotNull(message = "Id không được để trống!")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer userId;

    @NotBlank(message = "Họ tên không được để trống!")
    @Column(name = "name", columnDefinition = "varchar(50)")
    private String name;

    @Column(name = "`password`", columnDefinition = "VARCHAR(255)")
    private String password;

    @Column(name = "avatar", columnDefinition = "varchar(255)")
    private String avatar;

    @Column(name = "birthday", columnDefinition = "varchar(50)")
    private String birthday;

    @NotNull(message = "Giới tính không được để trống!")
    @Column(name = "gender", columnDefinition = "bit(1)")
    private Boolean gender;

    @Column(name = "fate", columnDefinition = "varchar(15)")
    private String fate;

    @Column(name = "height")
    private Float height;

    @Column(name = "weight")
    private Float weight;

    @Column(name = "email", columnDefinition = "VARCHAR(50) UNIQUE NOT NULL")
    private String email;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "roleId"))
    private Set<Role> roles = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id", referencedColumnName = "cart_id")
    private Cart cart;

    public User(Integer userId, String name, String password, String avatar, String birthday, Boolean gender, String fate, Float height, Float weight, String email, Set<Role> roles, Cart cart) {
        this.userId = userId;
        this.name = name;
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

    public User(Integer userId, String name, String password, String avatar, String birthday, Boolean gender, String email, Set<Role> roles) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.avatar = avatar;
        this.birthday = birthday;
        this.gender = gender;
        this.email = email;
        this.roles = roles;
    }

    public User(UserDTO userDTO) {
        this.userId = userDTO.getId();
        this.name = userDTO.getName();
        this.avatar = userDTO.getAvatar();
        this.birthday = userDTO.getBirthday();
        this.gender = userDTO.getGender();
        this.email = userDTO.getEmail();
        this.password = userDTO.getPassword();
        this.roles = (Set<Role>) userDTO.getRoles().stream().map(
                grantedAuthority -> new Role(Integer.parseInt(grantedAuthority.getAuthority()))
        ).toList();
    }


    public void editUser(UserDTO userDTO){
        this.name = userDTO.getName();
        this.avatar = userDTO.getAvatar();
        this.birthday = userDTO.getBirthday();
        this.gender = userDTO.getGender();
    }

}
