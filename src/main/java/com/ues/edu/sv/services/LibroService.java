package com.ues.edu.sv.services;

import com.ues.edu.sv.dto.libros.crear.LibroSaveRequest;
import com.ues.edu.sv.dto.libros.editar.LibroEditRequest;
import com.ues.edu.sv.dto.libros.listar.LibrosResponse;
import com.ues.edu.sv.entities.Biblioteca;
import com.ues.edu.sv.entities.Libro;
import com.ues.edu.sv.exceptions.ConflictException;
import com.ues.edu.sv.exceptions.NotFoundException;
import com.ues.edu.sv.interfaces.Crud;
import com.ues.edu.sv.mappers.LibrosMapper;
import com.ues.edu.sv.repositories.BibliotecaRepository;
import com.ues.edu.sv.repositories.LibroRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LibroService implements Crud<LibrosResponse, Integer, LibroSaveRequest, LibroEditRequest> {

    private final LibroRepository libroRepository;
    private final LibrosMapper librosMapper;

    private final BibliotecaRepository bibliotecaRepository;

    public LibroService(LibroRepository libroRepository, LibrosMapper librosMapper, BibliotecaRepository bibliotecaRepository) {
        this.libroRepository = libroRepository;
        this.bibliotecaRepository = bibliotecaRepository;
        this.librosMapper = librosMapper;
    }

    @Override
    public Page<LibrosResponse> findAll(Pageable pageable) {
        Page<Libro> libros = this.libroRepository.findAll(pageable);
        return libros.map(this.librosMapper);
    }

    @Override
    public LibrosResponse findById(Integer id) {
        Libro libro = this.libroRepository.findById(id).orElseThrow(() -> new NotFoundException("Libro no encontrado"));
        return this.librosMapper.apply(libro);
    }

    @Override
    public LibrosResponse save(LibroSaveRequest libro) {

        this.bibliotecaRepository.findById(libro.biblioteca().id()).orElseThrow(() -> new NotFoundException("Biblioteca no encontrada"));
        this.libroRepository.findByNombre(libro.nombre()).ifPresent(libro1 -> {
            throw new ConflictException("Ya existe un libro con ese nombre");
        });

        Libro libroToSave = new Libro(
                libro.nombre(),
                new Biblioteca(libro.biblioteca().id())
        );
        return librosMapper.apply(this.libroRepository.save(libroToSave));
    }

    @Override
    public LibrosResponse edit(Integer id, LibroEditRequest libro) {
        Biblioteca biblioteca = null;
        if(
                libro.biblioteca() != null
        ){
            biblioteca = this.bibliotecaRepository.findById(libro.biblioteca().id()).orElseThrow(() -> new NotFoundException("Biblioteca no encontrada"));
        }

        Libro libroToEdit = this.libroRepository.findById(id).orElseThrow(() -> new NotFoundException("Libro no encontrado"));

        this.libroRepository.findByNombre(libro.nombre()).ifPresent(libro1 -> {
            if (libro1.getId() != id) {
                throw new ConflictException("Ya existe un libro con ese nombre");
            }
        });

        libroToEdit.setNombre(libro.nombre());

        if ( biblioteca != null &&  biblioteca.getId() != libroToEdit.getBiblioteca().getId()) {
            libroToEdit.setBiblioteca(biblioteca);
        }

        return librosMapper.apply(this.libroRepository.save(libroToEdit));
    }

    @Override
    public void delete(Integer id) {
        Libro libro = this.libroRepository.findById(id).orElseThrow(() -> new NotFoundException("Libro no encontrado"));
        this.libroRepository.delete(libro);
    }


}
