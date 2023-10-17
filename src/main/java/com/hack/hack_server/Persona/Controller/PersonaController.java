package com.hack.hack_server.Persona.Controller;

import com.hack.hack_server.Authentication.PrincipalDetails;
import com.hack.hack_server.Persona.Dto.PersonaListRequestDto;
import com.hack.hack_server.Persona.Dto.PetRequestDto;
import com.hack.hack_server.Persona.Dto.SpeciesRequestDto;
import com.hack.hack_server.Persona.Service.PersonaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("persona")
@RequiredArgsConstructor
public class PersonaController {
    private final PersonaService personaService;

    @PostMapping("/save")
    public ResponseEntity savePersona(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestBody PetRequestDto requestDto){
        return personaService.savePetInfo(principalDetails, requestDto);
    }

    @GetMapping("/list")
    public PersonaListRequestDto getPersonaList(@AuthenticationPrincipal PrincipalDetails principalDetails){
        return personaService.findPersonaList(principalDetails);
    }

    @DeleteMapping("/delete/{pet_id}")
    public ResponseEntity deletePersona(@PathVariable Long pet_id){
        return personaService.deletePersona(pet_id);
    }


}
