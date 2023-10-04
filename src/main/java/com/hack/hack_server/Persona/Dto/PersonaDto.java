package com.hack.hack_server.Persona.Dto;

import com.hack.hack_server.Entity.Pet;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PersonaDto {
    private String name;
    private Long petId;
    private String petProfile;

    public PersonaDto(Pet pet){
        this.name = pet.getName();
        this.petId = pet.getId();
        this.petProfile = pet.getPetProfile();
    }
}
