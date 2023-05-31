package com.ues.edu.sv.dto.libros.listar;

public record LibrosResponse(
        int id,
        String nombre,
        BibliotecaLibroResponse biblioteca
) {
}
