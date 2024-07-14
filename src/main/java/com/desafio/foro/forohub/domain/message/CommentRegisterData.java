package com.desafio.foro.forohub.domain.message;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

public record CommentRegisterData(
    @NotNull
    Long userId,
    @NotBlank
    String title,
    @NotBlank
    String body,
    @NotBlank
    String topic
) {

}
