package com.example.be.entity.dto;

import com.example.be.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserDTO implements UserDetails {

    private Integer id;

    private String name;

    private String avatar;

    private String birthday;

    private String fate;

    private Integer gender;

    private String email;

    private String password;

    private String height;

    private String weight;

    private Collection<? extends GrantedAuthority> roles;

    public UserDTO(Integer id, String username, String password, Collection<? extends GrantedAuthority> roles) {
        this.id = id;
        this.email = username;
        this.password = password;
        this.roles = roles;
    }

    public UserDTO(User user) {
        this.id = user.getUserId();
        this.name = user.getName();
        this.avatar = user.getAvatar();
        this.birthday = user.getBirthday();
        this.fate = user.getFate();
        this.gender = user.getGender();
        this.height = user.getHeight();
        this.weight = user.getWeight();
    }

    public static UserDTO build(User user){
        System.out.println(user.getRoles());
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
        return new UserDTO(user.getUserId(),user.getEmail(),user.getPassword(),authorities);
    }

    public UserDTO() {
    }

    public UserDTO(String username, String password, List<String> roles) {
        this.email = username;
        this.password = password;
        this.roles = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
    }

    public UserDTO(Integer id, String name, String avatar, String birthday, String fate,Integer gender, String height, String weight) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.birthday = birthday;
        this.fate = fate;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getFate() {
        return fate;
    }

    public void setFate(String fate) {
        this.fate = fate;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<? extends GrantedAuthority> getRoles() {
        return roles;
    }

    public void setRoles(Collection<? extends GrantedAuthority> roles) {
        this.roles = roles;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
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
