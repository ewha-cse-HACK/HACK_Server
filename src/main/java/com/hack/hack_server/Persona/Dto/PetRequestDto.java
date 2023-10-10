package com.hack.hack_server.Persona.Dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
public class PetRequestDto {
    private String speciesName; //반려동물 종류
    private String name; //반려동물 이름
    private String charOne; //성격 1
    private String charTwo; //성격 2
    private String ownerName;
    private String favoritePlay;
    private String favoriteSnack;
    private String favoriteTime;
    private String habit;
    private String favoritePlace;
    private String routine;
    private String petImage;
    private Date passedDate;
    private String furColor;
    private String kind;

}