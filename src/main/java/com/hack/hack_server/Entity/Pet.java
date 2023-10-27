package com.hack.hack_server.Entity;

import com.hack.hack_server.Persona.Dto.PetRequestDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import java.sql.Date;

@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Pet extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_id")
    private Long id;

    private String name;

    @Column(name = "image")
    @Setter
    private String petProfile;

    @Column(name = "favorite_play")
    private String favoritePlay;

    @Column(name = "favorite_snack")
    private String favoriteSnack;

    @Column(name = "favorite_time")
    private String favoriteTime;

    private String habit;

    @Column(name = "favorite_place")
    private String favoritePlace;

    private String routine;

    @Column(name = "owner_name")
    private String ownerName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", referencedColumnName = "user_id")
    private User user;

    @Column(name = "species")
    private String species;

    @Column(name = "passed_date")
    private Date passedDate;

    @Column(name = "charac_one")
    private String characOne;

    @Column(name = "charac_two")
    private String characTwo;

    @Column(name = "kind")
    private String kind;

    @Column(name = "fur_color")
    private String furColor;


    @Builder
    public Pet(User user, PetRequestDto requestDto){
        this.user = user;
        this.species = requestDto.getSpeciesName();
        this.name = requestDto.getName();
        this.ownerName = requestDto.getOwnerName();
        this.favoritePlace = requestDto.getFavoritePlace();
        this.favoritePlay = requestDto.getFavoritePlay();
        this.habit = requestDto.getHabit();
        this.routine = requestDto.getRoutine();
        this.favoriteSnack = requestDto.getFavoriteSnack();
        this.favoriteTime = requestDto.getFavoriteTime();
        this.petProfile = requestDto.getPetImage();
        this.passedDate = requestDto.getPassedDate();
        this.kind = requestDto.getKind();
        this.furColor = requestDto.getFurColor();
        this.characOne = requestDto.getCharOne();
        this.characTwo = requestDto.getCharTwo();
    }
}
