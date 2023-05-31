package com.ues.edu.sv.dto.biblioteca.crear;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record BibliotecaSaveDTO (
        @NotNull(message = "El nombre de la biblioteca no puede ser nulo")
        @NotEmpty(message = "El nombre de la biblioteca no puede estar vacío")
        String nombre,
        
        List<LibroSaveDto> libros


) {
}
