package com.desafio.foro.forohub.domain.message;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findByStatusTrue(Pageable pageable);

    Boolean existsByTitle(String title);
    Boolean existsByBody(String body);

}
