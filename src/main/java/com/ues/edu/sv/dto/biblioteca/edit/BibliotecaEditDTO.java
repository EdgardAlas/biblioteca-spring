package com.ues.edu.sv.dto.biblioteca.edit;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record BibliotecaEditDTO(
        @NotNull(message = "El nombre de la biblioteca no puede ser nulo")
        @NotEmpty(message = "El nombre de la biblioteca no puede estar vacío")
        String nombre) {
}
