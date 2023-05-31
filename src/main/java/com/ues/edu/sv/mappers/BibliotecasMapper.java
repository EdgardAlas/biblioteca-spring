package com.ues.edu.sv.mappers;

import com.ues.edu.sv.dto.biblioteca.listar.BibliotecaResponse;
import com.ues.edu.sv.dto.biblioteca.listar.LibroResponse;
import com.ues.edu.sv.entities.Biblioteca;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class BibliotecasMapper implements Function<Biblioteca, BibliotecaResponse> {
    @Override
    public BibliotecaResponse apply(Biblioteca biblioteca) {
        return new BibliotecaResponse(
                biblioteca.getId(),
                biblioteca.getNombre(),
                biblioteca.getLibros().stream().map(libro -> new LibroResponse(
                        libro.getId(),
                        libro.getNombre()
                )).toList()
        );
    }
}
