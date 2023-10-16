package com.hack.hack_server.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class JournalComment extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column
    private String content;

    @OneToOne
    @JoinColumn(name = "journal_id", referencedColumnName = "journal_id")
    private Journal journal;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @Column(columnDefinition="tinyint(0) default 0")
    @Setter
    private boolean isDel;

    @Builder
    public JournalComment(String content, User user, Journal journal){
        this.content = content;
        this.user = user;
        this.journal = journal;
    }


//    //작성한 그림일기 댓글 수정 기능
//    public void update(String content){
//        this.content = content;
//    }
}
