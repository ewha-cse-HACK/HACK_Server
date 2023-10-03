package com.hack.hack_server.Persona.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class PersonaListRequestDto {
    private List<PersonaDto> personaList;
    private int listSize;
}
