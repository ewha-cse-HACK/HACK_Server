package com.hack.hack_server.Persona.Controller;

import com.hack.hack_server.Persona.Dto.SpeciesRequestDto;
import com.hack.hack_server.Persona.Service.PersonaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rainbowletter/persona")
@RequiredArgsConstructor
public class PersonaController {
    private final PersonaService personaService;

    @PostMapping("/species")
    public Long saveSpecies(@RequestBody SpeciesRequestDto requestDto){
        return personaService.saveSpecies(requestDto);
    }

    @PostMapping("/species/new")
    public ResponseEntity addNewSpecies(@RequestBody SpeciesRequestDto requestDto){
        return personaService.addSpecies(requestDto);
    }

}
