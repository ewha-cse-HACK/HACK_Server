package com.hack.hack_server.Entity;

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
public class Pet extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_id")
    private Long id;

    private String name;

    @Column(name = "profile_img")
    private String profileImg;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "species_id", referencedColumnName = "species_id")
    private Species species;

    @Builder
    public Pet(User user, Species species, String name){
        this.user = user;
        this.species = species;
        this.name = name;
    }
}
