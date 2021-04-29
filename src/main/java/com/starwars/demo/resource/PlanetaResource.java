package com.starwars.demo.resource;

import com.starwars.demo.dto.BasicResponse;
import com.starwars.demo.dto.PlanetaDTO;
import com.starwars.demo.service.PlanetaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/planetas")
public class PlanetaResource {

    @Autowired
    public PlanetaService planetaService;


    @PostMapping
    public ResponseEntity<PlanetaDTO> save(@RequestBody PlanetaDTO planetaDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(planetaService.save(planetaDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@RequestBody PlanetaDTO planetaDTO,
                                         @PathVariable("id") String id) {

        if(planetaDTO.get_id() == null || planetaDTO.get_id().trim().equalsIgnoreCase("")) {
            return ResponseEntity.badRequest().body(new BasicResponse("Id não pode ser nulo!"));
        } else {
            if(planetaService.getById(id).isPresent()) {
                return ResponseEntity.ok(planetaService.update(planetaDTO));
            }
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<PlanetaDTO>> listAll() {
        return ResponseEntity.ok(planetaService.listAll());
    }

    @GetMapping("/")
    public ResponseEntity<PlanetaDTO> getByName(@RequestParam("nome") String nome) {
        Optional<PlanetaDTO> result = planetaService.getByNome(nome);

        if(result.isPresent()) {
            return ResponseEntity.ok(result.get());
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanetaDTO> getById(@PathVariable("id") String id) {
        Optional<PlanetaDTO> result = planetaService.getById(id);

        if(result.isPresent()) {
            return ResponseEntity.ok(result.get());
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable("id") String id) {

        planetaService.remove(id);
        return ResponseEntity.ok().build();
    }
}
