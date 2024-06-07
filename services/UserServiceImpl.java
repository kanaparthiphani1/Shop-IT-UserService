package com.example.userservice.services;

import com.example.userservice.models.Role;
import com.example.userservice.models.User;
import com.example.userservice.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserRepository userRepository;

    public UserServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }

    public User signUp(String email,
                       String name,
                       String password, List<Role> roles) {

        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setRoles(roles);

        user.setEmailVerified(true);
        return userRepository.save(user);
    }
}
