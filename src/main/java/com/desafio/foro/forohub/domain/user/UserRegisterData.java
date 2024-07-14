package com.desafio.foro.forohub.domain.user;

import jakarta.validation.constraints.NotBlank;

public record UserRegisterData(
    @NotBlank
    String username,
    @NotBlank
    String email,
    @NotBlank
    String password) {

}
