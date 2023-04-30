package com.example.be;

import com.example.be.entity.Role;
import com.example.be.entity.User;
import com.example.be.repository.RoleRepository;
import com.example.be.repository.UserRepository;
import com.example.be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.HashSet;

@SpringBootApplication
public class BeApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeApplication.class, args);
    }

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Bean
    CommandLineRunner run(UserService userService) {

        return args -> {
            if(roleRepository.findAll().spliterator().getExactSizeIfKnown()==0){
                roleRepository.save(new Role("ROLE_ADMIN"));
                roleRepository.save(new Role("ROLE_MEMBER"));
            }
            if(userRepository.findAll().spliterator().getExactSizeIfKnown()==0){
                userRepository.save(
                        new User(105, "PVK", encoder.encode("123456"), null, null, 1, "duongdaiphuoc511@gmail.com", new HashSet<Role>(Arrays.asList(new Role("ROLE_MEMBER")))));
            }
        };

    }
}
