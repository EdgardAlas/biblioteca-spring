package com.ues.edu.sv.dto.libros.crear;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record LibroSaveDTO(
        @NotEmpty(message = "El nombre no puede ser vacio")
        @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
        String nombre,
        @Valid
        BibliotecaLibroSaveDTO biblioteca
) {
}