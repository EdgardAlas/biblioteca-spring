package com.ues.edu.sv.mappers;

import com.ues.edu.sv.dto.libros.listar.BibliotecaLibroResponse;
import com.ues.edu.sv.dto.libros.listar.LibrosResponse;
import com.ues.edu.sv.entities.Libro;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class LibrosMapper implements Function<Libro, LibrosResponse> {
    @Override
    public LibrosResponse apply(Libro libro) {
        return new LibrosResponse(
                libro.getId(),
                libro.getNombre(),
                new BibliotecaLibroResponse(
                        libro.getBiblioteca().getId(),
                        libro.getBiblioteca().getNombre()
                ));
    }
}
