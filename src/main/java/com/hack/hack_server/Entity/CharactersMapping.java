package com.hack.hack_server.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class CharactersMapping extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "pet_id", referencedColumnName = "pet_id")
    private Pet pet;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "characters_id", referencedColumnName = "characters_id")
    private Characters character;

    @Builder
    public CharactersMapping(Pet pet, Characters character){
        this.pet = pet;
        this.character = character;
    }

}