package com.hack.hack_server.Persona.Service;

import com.hack.hack_server.Entity.Pet;
import com.hack.hack_server.Entity.Species;
import com.hack.hack_server.Entity.User;
import com.hack.hack_server.Persona.Dto.SpeciesRequestDto;
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
    private final UserRepository userRepository;

    @Transactional
    public Long saveSpecies(SpeciesRequestDto requestDto){
        Species species = speciesRepository.findSpeciesBySpeciesName(requestDto.getSpeciesName());
        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다: " + requestDto.getUserId()));
        Pet pet = Pet.builder()
                .user(user)
                .species(species)
                .build();
        petRepository.save(pet);
        return pet.getId();
    }

    @Transactional
    public ResponseEntity addSpecies(SpeciesRequestDto requestDto){
       if (speciesRepository.existsBySpeciesName(requestDto.getSpeciesName())){
           return new ResponseEntity(HttpStatus.BAD_REQUEST);
       }
       Species species = Species.builder()
               .speciesName(requestDto.getSpeciesName())
               .build();
       speciesRepository.save(species);
//       return species.getId();
        return new ResponseEntity(HttpStatus.OK);
    }

}
