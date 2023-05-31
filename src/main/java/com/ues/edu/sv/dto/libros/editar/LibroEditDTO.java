package com.ues.edu.sv.dto.libros.editar;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LibroEditDTO(

        @NotEmpty(message = "El nombre no puede ser vacio")
        @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
        String nombre,
        @Valid
        BibliotecaLibroEditDTO biblioteca
) {
}