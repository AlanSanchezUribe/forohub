package com.desafio.foro.forohub.domain.user;

import jakarta.validation.constraints.NotNull;

public record UpdateUserData(@NotNull Long id, String username, String password) {

}
