package com.ues.edu.sv.dto.libros.crear;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record BibliotecaLibroSaveRequest(
        @NotNull(message = "El id de la biblioteca no puede ser nulo")
        @Min(value = 1, message = "El id de la biblioteca debe ser mayor a 0")
        int id
) {
}
