package com.example.be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
@EnableJpaRepositories
public class BeApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeApplication.class, args);
    }

//    @Bean
//    BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    CommandLineRunner run(UserService userService) {
//        return args -> {
//            userService.saveRole(new Role(1, "ADMIN"));
//            userService.saveRole(new Role(2, "CUSTOMER"));
//
//            userService.saveUser(new User(1, "Dai Phuoc", "phuoc", "12345678", "", "2001-01-05", 1, "Kim", null, null, "duongdaiphuoc511@gmail.com", new HashSet<>(), null));
//
//            userService.addToUser("duongdaiphuoc511@gmail.com", "CUSTOMER");
//        };
//    }
}
