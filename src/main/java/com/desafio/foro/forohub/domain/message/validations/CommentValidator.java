package com.desafio.foro.forohub.domain.message.validations;

import com.desafio.foro.forohub.domain.message.CommentRegisterData;

public interface CommentValidator {
    public void validate(CommentRegisterData commentRegisterData);

}
