package com.example.be.service;

import com.example.be.entity.Role;
import com.example.be.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> getAllUser();
    User saveUser(User user);
    Role saveRole(Role role);
    void addToUser(String username, String roleName);
}
