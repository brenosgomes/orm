package com.brenosgomes.testepersistencia.dto;

import com.brenosgomes.testepersistencia.domain.Filme;
import lombok.Data;

import java.io.Serializable;

@Data
public class FilmeDTO implements Serializable {
    private static final long serialVersionUID= 1;

    private Integer id;
    private String nome;
    private Integer duracao;
    private Float nota;
    private Integer rank;

    private Integer cinema_id;

    public FilmeDTO(Filme obj) {
        id = obj.getId();
        nome = obj.getNome();
        duracao = obj.getDuracao();
        nota = obj.getNota();
        rank = obj.getRank();
    }

    public FilmeDTO(){}
}
