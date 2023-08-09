package com.hack.hack_server.Repository;

import com.hack.hack_server.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT c from Comment c where c.isDel = false")
    List<Comment> findByPost_Id(Long postId);
}
