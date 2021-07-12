package com.brenosgomes.testepersistencia.repositories;

import com.brenosgomes.testepersistencia.domain.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Integer> {
}
