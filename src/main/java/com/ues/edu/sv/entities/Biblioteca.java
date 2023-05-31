package com.ues.edu.sv.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "biblioteca")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Biblioteca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nombre;

    @OneToMany(
            mappedBy = "biblioteca",
            cascade = CascadeType.ALL
    )
    private Set<Libro> libros = new HashSet<>();

    public Biblioteca(int id) {
        this.id = id;
    }

    public Biblioteca(String nombre) {
        this.nombre = nombre;
    }

    public void setLibros(Set<Libro> libros){
        this.libros = libros;

        for (Libro libro:
                libros) {
            libro.setBiblioteca(this);
        }
    }
}

