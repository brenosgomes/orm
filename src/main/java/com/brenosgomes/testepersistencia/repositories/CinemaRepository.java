package com.brenosgomes.testepersistencia.repositories;

import com.brenosgomes.testepersistencia.domain.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Integer> {
}
