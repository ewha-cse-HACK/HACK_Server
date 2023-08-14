package com.hack.hack_server.Persona.Controller;

import com.hack.hack_server.Authentication.PrincipalDetails;
import com.hack.hack_server.Persona.Dto.SpeciesRequestDto;
import com.hack.hack_server.Persona.Service.PersonaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public Long saveSpecies(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestBody SpeciesRequestDto requestDto){
        return personaService.saveSpecies(principalDetails, requestDto);
    }

    @PostMapping("/species/new")
    public ResponseEntity addNewSpecies(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestBody SpeciesRequestDto requestDto){
        return personaService.addSpecies(principalDetails, requestDto);
    }

}
