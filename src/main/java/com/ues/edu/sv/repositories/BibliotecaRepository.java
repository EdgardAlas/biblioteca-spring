package com.ues.edu.sv.repositories;

import com.ues.edu.sv.entities.Biblioteca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BibliotecaRepository extends JpaRepository<Biblioteca, Integer> {
}
