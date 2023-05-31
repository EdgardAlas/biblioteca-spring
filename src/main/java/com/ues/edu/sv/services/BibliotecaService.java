package com.ues.edu.sv.services;

import com.ues.edu.sv.dto.biblioteca.crear.BibliotecaSaveDTO;
import com.ues.edu.sv.dto.biblioteca.edit.BibliotecaEditDTO;
import com.ues.edu.sv.dto.biblioteca.listar.BibliotecaResponse;
import com.ues.edu.sv.entities.Biblioteca;
import com.ues.edu.sv.entities.Libro;
import com.ues.edu.sv.exceptions.NotFoundException;
import com.ues.edu.sv.mappers.BibliotecasMapper;
import com.ues.edu.sv.repositories.BibliotecaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class BibliotecaService {
    private final BibliotecaRepository bibliotecaRepository;
    private final BibliotecasMapper bibliotecasMapper;

    public BibliotecaService(BibliotecaRepository bibliotecaRepository, BibliotecasMapper bibliotecasMapper) {
        this.bibliotecaRepository = bibliotecaRepository;
        this.bibliotecasMapper = bibliotecasMapper;
    }

    public Page<BibliotecaResponse> findAll(Pageable pageable) {
        Page<Biblioteca> bibliotecas = this.bibliotecaRepository.findAll(pageable);
        return bibliotecas.map(this.bibliotecasMapper);
    }

    public BibliotecaResponse findById(int id) {
        Biblioteca biblioteca = this.bibliotecaRepository.findById(id).orElseThrow(() -> new NotFoundException("Biblioteca no encontrada"));
        return this.bibliotecasMapper.apply(biblioteca);
    }

    public BibliotecaResponse save(BibliotecaSaveDTO biblioteca) {


        Biblioteca bibliotecaToSave = new Biblioteca(biblioteca.nombre());
        var libros = biblioteca.libros().stream().map(libro -> new Libro(libro.nombre())).toList();


        bibliotecaToSave.setLibros(new HashSet<>(libros));

        return this.bibliotecasMapper.apply(this.bibliotecaRepository.save(bibliotecaToSave));
    }

    public BibliotecaResponse edit(int id, BibliotecaEditDTO biblioteca) {
        Biblioteca bibliotecaToEdit = this.bibliotecaRepository.findById(id).orElseThrow(() -> new NotFoundException("Biblioteca no encontrada"));
        bibliotecaToEdit.setNombre(biblioteca.nombre());
        return this.bibliotecasMapper.apply(this.bibliotecaRepository.save(bibliotecaToEdit));

    }

    public void delete(int id) {
        Biblioteca biblioteca = this.bibliotecaRepository.findById(id).orElseThrow(() -> new NotFoundException("Libro no encontrado"));
        this.bibliotecaRepository.delete(biblioteca);
    }
}
