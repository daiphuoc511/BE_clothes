package com.example.be.entity.dto;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtResponse {
    private String token;

    private String type="Bearer";

    private UserDTO userDTO;

    private Collection<? extends GrantedAuthority> roles;

    public JwtResponse(String token, UserDTO userDTO, Collection<? extends GrantedAuthority> roles) {
        this.token = token;
        this.userDTO = userDTO;
        this.roles = roles;
    }

    public Collection<? extends GrantedAuthority> getRoles() {
        return roles;
    }

    public void setRoles(Collection<? extends GrantedAuthority> roles) {
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
}
