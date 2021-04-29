package com.starwars.demo.dto;

import lombok.Data;

@Data
public class PlanetaDTO {

    private String _id;

    private String nome;

    private String clima;

    private String terreno;

    private Integer qtdAparicoes;

}
