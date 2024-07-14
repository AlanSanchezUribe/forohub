package com.desafio.foro.forohub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

import com.desafio.foro.forohub.domain.user.AuthUserData;
import com.desafio.foro.forohub.domain.user.User;
import com.desafio.foro.forohub.infra.security.TokenData;
import com.desafio.foro.forohub.infra.security.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenData> login(@RequestBody @Valid AuthUserData authUserData) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(authUserData.email(), authUserData.password()); 
        var authUser = authenticationManager.authenticate(authentication);
        var token = tokenService.generateToken((User) authUser.getPrincipal());

        return ResponseEntity.ok(new TokenData(token));
        
    }

}
