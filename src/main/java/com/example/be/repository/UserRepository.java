package com.example.be.repository;

import com.example.be.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserByUserId(Integer userId);

    User findByEmail(String email);

    @Query(value = "update users set users.name = ?2, users.birthday = ?3, users.gender = ?4, users.height = ?5, users.weight = ?6, users.avatar = ?7 WHERE users.user_id = ?1", nativeQuery = true)
    User saveUser(Integer userId, String name, String birthday, Integer gender, Float height, Float weight, String avatarUrl);
}
