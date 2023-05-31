package com.ues.edu.sv.repositories;

import com.ues.edu.sv.entities.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Integer>{

    @Query("SELECT l FROM Libro l WHERE l.nombre = ?1")
    Optional<Libro> findByNombre(String nombre);
}
