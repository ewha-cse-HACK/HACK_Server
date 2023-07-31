package com.hack.hack_server.Community.Repository;

import com.hack.hack_server.Community.Entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT post from Post post where post.isDel = false")
    Page<Post> findAllByDelIsFalse(Pageable pageable);
}
