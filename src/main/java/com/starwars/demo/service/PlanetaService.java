package com.starwars.demo.service;

import com.starwars.demo.dto.PlanetaDTO;
import com.starwars.demo.model.Planeta;
import com.starwars.demo.repository.PlanetaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlanetaService {

    @Autowired
    private PlanetaRepository planetaRepository;


    @Autowired
    private ModelMapper modelMapper;

    public PlanetaDTO save(PlanetaDTO planetaDTO){
        Planeta planeta = convertToEntity(planetaDTO);
        return convertToDto(planetaRepository.save(planeta));
    }

    public List<PlanetaDTO> listAll(){
        return planetaRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public Optional<PlanetaDTO> getByNome(String nome) {
        return planetaRepository.findByNome(nome);
    }

    public Optional<PlanetaDTO> getById(String id) {
        Optional<Planeta> result = planetaRepository.findById(id);
        return result.map(this::convertToDto);
    }

    public PlanetaDTO update(PlanetaDTO planetaDTO) {
        return convertToDto(planetaRepository.save(convertToEntity(planetaDTO)));
    }

    public void remove(String id) {
        planetaRepository.deleteById(id);
    }


    private PlanetaDTO convertToDto(Planeta planeta) {
        PlanetaDTO planetaDTO  = modelMapper.map(planeta, PlanetaDTO.class);

        return planetaDTO;
    }

    private Planeta convertToEntity(PlanetaDTO planetaDTO) {
        Planeta planeta = modelMapper.map(planetaDTO, Planeta.class);

//        if (planetaDTO.get) != null) {
//            Post oldPost = postService.getPostById(postDto.getId());
//            post.setRedditID(oldPost.getRedditID());
//            post.setSent(oldPost.isSent());
//        }
        return planeta;
    }
}
