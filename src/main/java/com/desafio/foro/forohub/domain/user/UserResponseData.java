package com.desafio.foro.forohub.domain.user;

public record UserResponseData(Long id, String username, String email) {

    public UserResponseData(User user) {
        this(user.getId(), user.getUsername(), user.getEmail());
    }

}
