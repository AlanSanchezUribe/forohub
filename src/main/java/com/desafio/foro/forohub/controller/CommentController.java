package com.desafio.foro.forohub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.foro.forohub.domain.message.Comment;
import com.desafio.foro.forohub.domain.message.CommentDetailData;
import com.desafio.foro.forohub.domain.message.CommentRegisterData;
import com.desafio.foro.forohub.domain.message.CommentRepository;
import com.desafio.foro.forohub.domain.message.CommentUpdateData;
import com.desafio.foro.forohub.domain.user.User;
import com.desafio.foro.forohub.domain.user.UserRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<CommentDetailData> createComment(@RequestBody @Valid CommentRegisterData commentRegisterData) {
        User user = userRepository.getReferenceById(commentRegisterData.userId());
        var comment = new Comment(commentRegisterData.title(), commentRegisterData.body(), user, commentRegisterData.topic());
        commentRepository.save(comment);
        return ResponseEntity.ok(new CommentDetailData(comment));

    }

    @GetMapping
    public ResponseEntity<Page<CommentDetailData>> getComments(@PageableDefault(size = 4) Pageable pageable) {
        var comments = commentRepository.findByStatusTrue(pageable);
        return ResponseEntity.ok(comments.map(CommentDetailData::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<CommentDetailData> updateComment(@RequestBody @Valid CommentUpdateData commentRegisterData) {
        Comment comment = commentRepository.getReferenceById(commentRegisterData.id());
        comment.update(commentRegisterData);
        return ResponseEntity.ok(new CommentDetailData(comment));
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        Comment comment = commentRepository.getReferenceById(id);
        comment.deleteComment();
        return ResponseEntity.noContent().build();
    }



}
