package com.ues.edu.sv.dto.biblioteca.listar;

import java.util.List;

public record BibliotecaResponse(
        int id,
        String nombre,

        List<LibroResponse> libros

) {
}
