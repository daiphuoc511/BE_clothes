package com.example.be.service.Impl;

import com.example.be.entity.User;
import com.example.be.entity.dto.UserDTO;
import com.example.be.entity.dto.UserPrinciple;
import com.example.be.repository.RoleRepository;
import com.example.be.repository.UserRepository;
import com.example.be.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if(user==null) {
            throw new  UsernameNotFoundException(username);
        }
        return UserPrinciple.build(user);
    }

    @Override
    public UserDTO loadUserDetailByUserName(String name) {
        User user = userRepository.findByEmail(name);
        if (user==null) return null;
        return new UserDTO(user);
    }

    @Override
    public UserDTO saveNewUser(UserDTO user) {
        User newUser = new User(user);
        userRepository.save(newUser);
        return null;
    }

    @Override
    public UserDTO editUser(Integer id, UserDTO user) {
        User userEdit= userRepository.findUserByUserId(id);
        userEdit.editUser(user);
        userRepository.save(userEdit);
        return null;
    }

    @Override
    public UserDTO findUserById(Integer id) {
        User user = userRepository.findUserByUserId(id);
        return new UserDTO(user);
    }
}
