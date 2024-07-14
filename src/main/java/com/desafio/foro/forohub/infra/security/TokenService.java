package com.desafio.foro.forohub.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.desafio.foro.forohub.domain.user.User;

@Service
public class TokenService {
    @Value("${api.security.secret}")
    private String apiSecret;

    public String generateToken(User user) {
        try {
            Algorithm  algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("forohub")
                    .withSubject(user.getEmail())
                    .withClaim("id", user.getId())
                    .withExpiresAt(generateExpiresAt())
                    .sign(algorithm);

        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error al generar token jwt", exception);
        }
    }

        public String getSubject(String token) {
        try {
            var algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.require(
                    algorithm)
                    .withIssuer("forohub")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error al generar token jwt", exception);
        }

    }

        private Instant generateExpiresAt() {
            return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
        
    }

}
