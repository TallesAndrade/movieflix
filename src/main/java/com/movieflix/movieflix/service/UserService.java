package com.movieflix.movieflix.service;

import com.movieflix.movieflix.controller.request.UserRequest;
import com.movieflix.movieflix.controller.response.UserResponse;
import com.movieflix.movieflix.entity.User;
import com.movieflix.movieflix.mapper.UserMapper;
import com.movieflix.movieflix.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository,PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse registerUser(UserRequest userRequest) {
        User user = UserMapper.toUser(userRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User userSaved = repository.save(user);
        return UserMapper.toUserRespose(userSaved);
    }


}
