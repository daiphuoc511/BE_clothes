package com.example.be.service.Impl;

import com.example.be.entity.Role;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
    public User saveNewUser(UserDTO user) {
        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail().trim());
        newUser.setPassword(passwordEncoder.encode(user.getPassword().trim()));
        newUser.setGender(user.getGender());
        newUser.setBirthday(user.getBirthday());
        newUser.setRoles(new HashSet<>(Arrays.asList(new Role("ROLE_MEMBER"))));
        newUser.setFate(convertBirthdayToFate(user.getBirthday()));
        return userRepository.save(newUser);
    }

    @Override
    public String convertBirthdayToFate(String birthday) {
        String fate = "";
        String[][] can = { { "Canh", "Tan", "Nham", "Quy", "Giap", "At", "Binh", "Dinh", "Mau", "Ky" },
                { "4", "4", "5", "5", "1", "1", "2", "2", "3", "3" } };
        String[][] chi = { { "Than", "Dau", "Tuat", "Hoi", "Ti", "Suu", "Dan", "Mao", "Thin", "Ty", "Ngo", "Mui" },
                { "1", "1", "2", "2", "0", "0", "1", "1", "2", "2", "0", "0" } };
        String[] arr = birthday.split("/");
        int year = Integer.parseInt(arr[2]);
        int thiencan = year % 10;
        int diachi = year % 12;
        int a = Integer.parseInt(can[1][thiencan]) + Integer.parseInt(chi[1][diachi]);
        if (a > 5) {
            a = a - 5;
            if (a == 1) {
                fate = "Kim";
            }
            if (a == 2) {
                fate = "Thuy";
            }
            if (a == 3) {
                fate = "Hoa";
            }
            if (a == 4) {
                fate = "Tho";
            }
            if (a == 5) {
                fate = "Moc";
            }
        } else {
            if (a == 1) {
                fate = "Kim";
            }
            if (a == 2) {
                fate = "Thuy";
            }
            if (a == 3) {
                fate = "Hoa";
            }
            if (a == 4) {
                fate = "Tho";
            }
            if (a == 5) {
                fate = "Moc";
            }
        }
        return fate;
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
