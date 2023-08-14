package com.hack.hack_server.Repository;

import com.hack.hack_server.Entity.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeciesRepository extends JpaRepository<Species, Long> {

    Species findSpeciesBySpeciesName(String speciesName);
    boolean existsBySpeciesName(String speciesName);
}
