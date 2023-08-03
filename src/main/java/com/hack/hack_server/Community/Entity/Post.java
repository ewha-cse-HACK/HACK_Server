package com.hack.hack_server.Community.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    @Setter
    private int likecount;

    @ColumnDefault("0")
    @Setter
    private int viewcount;


    @Column(columnDefinition="tinyint(0) default 0")
    @Setter
    private boolean isDel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;
    @Builder
    public Post(String title, String content, User user){
        this.title = title;
        this.content = content;
        this.user = user;
    }

    /*게시글 수정*/
    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

}
