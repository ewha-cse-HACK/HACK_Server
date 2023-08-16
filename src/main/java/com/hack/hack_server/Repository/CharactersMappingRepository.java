package com.hack.hack_server.Repository;

import com.hack.hack_server.Entity.CharactersMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharactersMappingRepository extends JpaRepository<CharactersMapping, Long> {
    List<CharactersMapping> findByPet_Id(Long petId);
}
