package com.desafio.foro.forohub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.foro.forohub.domain.user.User;
import com.desafio.foro.forohub.domain.user.UserRegisterData;
import com.desafio.foro.forohub.domain.user.UserRepository;
import com.desafio.foro.forohub.domain.user.UserResponseData;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
     UserRepository userRepository;

     @Autowired
     PasswordEncoder passwordEncoder;

     @PostMapping
     @Transactional
     ResponseEntity<UserResponseData> createUser(@RequestBody @Valid UserRegisterData userRegisterData) {
        String password = passwordEncoder.encode(userRegisterData.password());
        var user = new User(userRegisterData.username(), userRegisterData.email(), password);
        userRepository.save(user);
        return ResponseEntity.ok(new UserResponseData(user));

     }

}
