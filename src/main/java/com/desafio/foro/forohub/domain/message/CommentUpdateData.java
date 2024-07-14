package com.desafio.foro.forohub.domain.message;

import jakarta.validation.constraints.NotNull;

public record CommentUpdateData(@NotNull Long id, String title, String body, String topic) {

}
