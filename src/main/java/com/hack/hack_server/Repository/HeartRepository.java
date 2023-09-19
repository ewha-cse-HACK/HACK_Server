package com.hack.hack_server.Repository;

import com.hack.hack_server.Entity.Heart;
import com.hack.hack_server.Entity.Post;
import com.hack.hack_server.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeartRepository extends JpaRepository<Heart, Long> {

    boolean existsByUserAndPost(User user, Post post);

    void deleteByUserAndPost(User user, Post post);

}
