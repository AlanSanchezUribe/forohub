package com.desafio.foro.forohub.domain.message;

public record CommentDetailData(Long id, String title, String body, String topic, String username) {

        public CommentDetailData(Comment comment) {
            this(comment.getId(), comment.getTitle(), comment.getBody(), comment.getTopic(), comment.getUser().getUsername());
        }

}
