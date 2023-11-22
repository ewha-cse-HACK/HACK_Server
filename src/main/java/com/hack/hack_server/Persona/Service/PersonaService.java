package com.hack.hack_server.Persona.Service;

import com.hack.hack_server.Authentication.PrincipalDetails;
import com.hack.hack_server.Entity.*;
import com.hack.hack_server.Persona.Dto.PersonaDto;
import com.hack.hack_server.Persona.Dto.PersonaListRequestDto;
import com.hack.hack_server.Persona.Dto.PetRequestDto;
import com.hack.hack_server.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonaService {
    private final PetRepository petRepository;
    private final JournalRepository journalRepository;

    @Transactional
    public ResponseEntity savePetInfo(PrincipalDetails principalDetails, PetRequestDto requestDto){
//        /*종 저장*/
//        Species species;ㅎ
//        if (!speciesRepository.existsBySpeciesName(requestDto.getSpeciesName())){
//            species = Species.builder()
//                    .speciesName(requestDto.getSpeciesName())
//                    .build();
//            speciesRepository.save(species);
//        }else
//            species = speciesRepository.findSpeciesBySpeciesName(requestDto.getSpeciesName());

        User user = principalDetails.getUser();


        /*pet 객체 생성*/
        Pet pet = Pet.builder()
                .user(user)
                .requestDto(requestDto)
                .build();

//        if (requestDto.getPetImage() == null)
//            pet.setPetProfile(species.getDefaultImage());
//        else
//            pet.setPetProfile(requestDto.getPetImage());

        petRepository.save(pet);

//        /*성격 저장*/
//        Characters charOne = charactersRepository.findCharactersByType(requestDto.getCharOne());
//        Characters charTwo = charactersRepository.findCharactersByType(requestDto.getCharTwo());
//
//        CharactersMapping charMapOne = CharactersMapping.builder()
//                .pet(pet)
//                .character(charOne)
//                .build();
//        mappingRepository.save(charMapOne);
//
//        CharactersMapping charMapTwo = CharactersMapping.builder()
//                .pet(pet)
//                .character(charTwo)
//                .build();
//        mappingRepository.save(charMapTwo);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Transactional
    public PersonaListRequestDto findPersonaList(PrincipalDetails principalDetails){
        User user = principalDetails.getUser();
        List<Pet> petList = petRepository.findByUser_Id(user.getId());
        List<PersonaDto> personaDtos = petList.stream().map(PersonaDto::new).collect(Collectors.toList());
        PersonaListRequestDto requestDto = PersonaListRequestDto.builder()
                .personaList(personaDtos)
                .listSize(personaDtos.size())
                .build();
        return requestDto;
    }

    @Transactional
    public ResponseEntity deletePersona(Long pet_id){
        Pet pet = petRepository.findById(pet_id)
                .orElseThrow(() -> new IllegalArgumentException("해당 반려동물 id가 없습니다: " + pet_id));
        petRepository.delete(pet);
        journalRepository.deleteByPet_Id(pet_id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
