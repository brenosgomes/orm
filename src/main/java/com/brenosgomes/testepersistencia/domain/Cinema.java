package com.brenosgomes.testepersistencia.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Cinema implements Serializable {
    private static final long serialVersionUID= 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String endereco;

    @OneToMany(mappedBy = "cinema")
    private List<Filme> filmes = new ArrayList<>();

    public Cinema(Integer id, String nome, String endereco) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
    }

    public Cinema(){}
}
