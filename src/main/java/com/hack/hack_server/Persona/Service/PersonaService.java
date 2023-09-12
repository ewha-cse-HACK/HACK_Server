package com.hack.hack_server.Persona.Service;

import com.hack.hack_server.Authentication.PrincipalDetails;
import com.hack.hack_server.Entity.*;
import com.hack.hack_server.Persona.Dto.PetRequestDto;
import com.hack.hack_server.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PersonaService {
    private final PetRepository petRepository;
    private final SpeciesRepository speciesRepository;
    private final CharactersRepository charactersRepository;
    private final CharactersMappingRepository mappingRepository;

    @Transactional
    public ResponseEntity savePetInfo(PrincipalDetails principalDetails, PetRequestDto requestDto){
        /*종 저장*/
        Species species;
        if (!speciesRepository.existsBySpeciesName(requestDto.getSpeciesName())){
            species = Species.builder()
                    .speciesName(requestDto.getSpeciesName())
                    .build();
            speciesRepository.save(species);
        }else
            species = speciesRepository.findSpeciesBySpeciesName(requestDto.getSpeciesName());

        User user = principalDetails.getUser();


        /*pet 객체 생성*/
        Pet pet = Pet.builder()
                .user(user)
                .species(species)
                .requestDto(requestDto)
                .build();

        if (requestDto.getPetImage() == null)
            pet.setPetImage(species.getDefaultImage());
        else
            pet.setPetImage(requestDto.getPetImage());

        petRepository.save(pet);


        /*성격 저장*/
        Characters charOne = charactersRepository.findCharactersByType(requestDto.getCharOne());
        Characters charTwo = charactersRepository.findCharactersByType(requestDto.getCharTwo());

        CharactersMapping charMapOne = CharactersMapping.builder()
                .pet(pet)
                .character(charOne)
                .build();
        mappingRepository.save(charMapOne);

        CharactersMapping charMapTwo = CharactersMapping.builder()
                .pet(pet)
                .character(charTwo)
                .build();
        mappingRepository.save(charMapTwo);
        return new ResponseEntity(HttpStatus.OK);
    }

}
