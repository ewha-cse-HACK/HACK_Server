package com.hack.hack_server.Community.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Post extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    private String title;

    private String content;

    @ColumnDefault("0")
    private Long likecount;

    @ColumnDefault("0")
    private Long viewcount;

    @Builder
    public Post(String title, String content){
        this.title = title;
        this.content = content;
    }

    /*게시글 수정*/
    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

    public void updateLikecount(Long likecount){
        this.likecount = likecount;
    }

    public void updateViewcount(Long viewcount){
        this.viewcount = viewcount;
    }
}
