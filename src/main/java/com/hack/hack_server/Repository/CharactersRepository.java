package com.hack.hack_server.Repository;

import com.hack.hack_server.Entity.Characters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharactersRepository extends JpaRepository<Characters, Long> {
    Characters findCharactersByType(String type);

}