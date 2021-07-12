package com.brenosgomes.testepersistencia.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Filme implements Serializable {
    private static final long serialVersionUID= 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private Integer duracao;
    private Float nota;
    private Integer rank;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="cinema_id")
    private Cinema cinema;

    public Filme(Integer id, String nome, Integer duracao, Float nota, Integer rank,Cinema cinema) {
        super();
        this.id = id;
        this.nome = nome;
        this.duracao = duracao;
        this.nota = nota;
        this.rank = rank;
        this.cinema = cinema;
    }

    public Filme(){}
}
