package com.ues.edu.sv.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "libros")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Libro {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @Column(nullable = false, unique = true)
        private String nombre;

        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "biblioteca_id")
        private Biblioteca biblioteca;

        public Libro(String nombre, Biblioteca biblioteca) {
                this.nombre = nombre;
                this.biblioteca = biblioteca;
        }

        public Libro(String nombre) {
                this.nombre = nombre;
        }

}
