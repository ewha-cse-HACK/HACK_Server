package com.hack.hack_server;

import com.hack.hack_server.Entity.Post;
import com.hack.hack_server.Repository.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    PostRepository postsRepository;

    @Test
    public void insertBaseTimeEntity() {
        //given
        LocalDateTime now = LocalDateTime.now();
        postsRepository.save(Post.builder()
                .title("title")
                .content("content")
                .build());

        //when
        List<Post> postsList = postsRepository.findAll();

        //then
        Post posts = postsList.get(0);

        System.out.println(">>>>>>>>> createDate=" + posts.getCreated_date() + ", modifiedDate = " + posts.getModified_date() + "<<<<<<<<<<");

        Assertions.assertThat(posts.getCreated_date()).isAfter(now);
        Assertions.assertThat(posts.getModified_date()).isAfter(now);
    }

}
