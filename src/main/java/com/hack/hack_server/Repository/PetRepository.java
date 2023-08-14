package com.hack.hack_server.Repository;

import com.hack.hack_server.Entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    Pet findByUserId(Long id);

//    @Transactional
//    @Modifying
//    @Query(nativeQuery = true, value = "update pet p set p.name=:name where pet_id=:pet_id")
//    int updateName(@Param("pet_id") Long petId, @Param("name") String name);
}