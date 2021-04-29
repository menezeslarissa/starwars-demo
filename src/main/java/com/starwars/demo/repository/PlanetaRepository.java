package com.starwars.demo.repository;


import com.starwars.demo.dto.PlanetaDTO;
import com.starwars.demo.model.Planeta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlanetaRepository extends MongoRepository<Planeta, String> {

    Optional<PlanetaDTO> findByNome(String nome);

}
