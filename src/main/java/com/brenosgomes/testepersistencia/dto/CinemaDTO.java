package com.brenosgomes.testepersistencia.dto;

import com.brenosgomes.testepersistencia.domain.Cinema;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class CinemaDTO implements Serializable {
    private static final long serialVersionUID= 1;

    private Integer id;
    private String nome;
    private String endereco;

    private List<FilmeDTO> filmes = new ArrayList<>();

    public CinemaDTO(Cinema obj){
        id = obj.getId();
        nome = obj.getNome();
        endereco = obj.getEndereco();
        this.filmes.addAll(obj.getFilmes().stream().map(FilmeDTO::new).collect(Collectors.toList()));
    }

    public CinemaDTO(){}

}
