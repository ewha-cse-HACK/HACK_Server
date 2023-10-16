package com.hack.hack_server.Repository;

import com.hack.hack_server.Entity.Journal;
import com.hack.hack_server.Entity.Pet;
import com.hack.hack_server.Entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface JournalRepository extends JpaRepository<Journal, Long> {
    @Query(nativeQuery = true, value = "select * from journal where pet_id=:pet_id and owner_id=:owner_id")
    Journal findByPetAndOwnerId(@Param("pet_id") Long petId, @Param("owner_id") Long owner_id);

    Optional<Journal> findById(Long journalId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update journal j set j.image=:image where journal_id=:journal_id")
    int updateImage(@Param("journal_id") Long journalId, @Param("image") String image);

    @Query(nativeQuery = true, value = "select * from journal where pet_id=:pet_id")
    Page<Journal> findAllByPet_Id(Pageable pageable, @Param("pet_id") Long petId);
}
