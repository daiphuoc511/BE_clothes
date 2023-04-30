package com.example.be.service;

import com.example.be.entity.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends UserDetailsService {
    UserDTO loadUserDetailByUserName(String name);
    UserDTO saveNewUser(UserDTO user);
    UserDTO editUser(Integer id,UserDTO user);
    UserDTO findUserById(Integer id);
}
