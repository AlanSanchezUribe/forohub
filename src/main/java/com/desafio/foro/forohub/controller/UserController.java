package com.desafio.foro.forohub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.foro.forohub.domain.user.UpdateUserData;
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

     @GetMapping
     public ResponseEntity<Page<UserResponseData>> getUsers(@PageableDefault(size = 2) Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return ResponseEntity.ok(users.map(UserResponseData::new));
     }

     @GetMapping("/{id}")
     public ResponseEntity<UserResponseData> getUserById(@PathVariable Long id) {
        User user = userRepository.getReferenceById(id);
        return ResponseEntity.ok(new UserResponseData(user));
     }

      @PutMapping
      @Transactional
      public ResponseEntity<UserResponseData> updateUser(@RequestBody @Valid UpdateUserData userUpdateData) {
        User user = userRepository.getReferenceById(userUpdateData.id());

        String password = null;
        if (userUpdateData.password() != null) {
           password = passwordEncoder.encode(userUpdateData.password());
        }
        
          user.updateUser(userUpdateData.username(), password);

          return ResponseEntity.ok(new UserResponseData(user));
        
       
      }

      @DeleteMapping("/{id}")
      @Transactional
      public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
      }
}
