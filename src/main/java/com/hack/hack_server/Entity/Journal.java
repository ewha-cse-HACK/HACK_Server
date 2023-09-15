package com.hack.hack_server.Entity;

import com.hack.hack_server.Persona.Dto.PetRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Journal extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "journal_id")
    private Long id;

    @Column(columnDefinition = "LONGTEXT")
    //그림 일기
    private String image;

    //일기 내용
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", referencedColumnName = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id", referencedColumnName = "pet_id")
    private Pet pet;

    @Builder
    public Journal(User user, Pet pet, String image, String content){
        this.user = user;
        this.pet = pet;
        this.image = image;
        this.content = content;
    }
}
