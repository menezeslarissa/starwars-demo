package com.starwars.demo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter

public class Planeta {

    @Id
    private String _id;

    private String nome;

    private String clima;

    private String terreno;

    private Integer qtdAparicoes;
}
