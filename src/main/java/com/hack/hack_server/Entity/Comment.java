package com.hack.hack_server.Entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Comment extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column
    private String comment;

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @Column(columnDefinition="tinyint(0) default 0")
    @Setter
    private boolean isDel;

    @Builder
    public Comment(String comment, User user, Post post){
        this.comment = comment;
        this.user = user;
        this.post = post;
    }

    public void update(String comment){
        this.comment = comment;
    }
}
