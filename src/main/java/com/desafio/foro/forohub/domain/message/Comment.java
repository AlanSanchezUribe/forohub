package com.desafio.foro.forohub.domain.message;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.desafio.foro.forohub.domain.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity(name = "comment")
@Table(name = "comments")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String body;
    private LocalDateTime created_at;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    private String topic;
    private Boolean status;


    public Comment(String title, String body, User user, String topic) {
        this.title = title;
        this.body = body;
        this.created_at = LocalDateTime.now();
        this.user = user;
        this.topic = topic;
        this.status = true;
    }


    public void update(CommentUpdateData commentUpdateData) {
        if (commentUpdateData.title() != null) {
            this.title = commentUpdateData.title();
        }
        if (commentUpdateData.body() != null) {
            this.body = commentUpdateData.body();
        }
        if (commentUpdateData.topic() != null) {
            this.topic = commentUpdateData.topic();
        }

    }


    public void deleteComment() {
        this.status = false;
    }

}
