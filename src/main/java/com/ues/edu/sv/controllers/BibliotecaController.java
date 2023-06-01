package com.ues.edu.sv.controllers;

import com.ues.edu.sv.dto.biblioteca.crear.BibliotecaSaveRequest;
import com.ues.edu.sv.dto.biblioteca.edit.BibliotecaEditRequest;
import com.ues.edu.sv.dto.biblioteca.listar.BibliotecaResponse;
import com.ues.edu.sv.services.BibliotecaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin({"http://localhost:3000", "http://localhost:4200"})
@RequestMapping("/bibliotecas")
public class BibliotecaController {

    private final BibliotecaService bibliotecaService;

    public BibliotecaController(BibliotecaService bibliotecaService) {
        this.bibliotecaService = bibliotecaService;
    }


    @GetMapping
    public ResponseEntity<Page<BibliotecaResponse>> findAll(Pageable pageable) {
        return new ResponseEntity<>(this.bibliotecaService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BibliotecaResponse> findById(@PathVariable int id) {
        return new ResponseEntity<>(this.bibliotecaService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BibliotecaResponse> save(@Valid @RequestBody BibliotecaSaveRequest biblioteca) {
        return new ResponseEntity<>(this.bibliotecaService.save(biblioteca), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BibliotecaResponse> edit(@PathVariable int id, @Valid @RequestBody BibliotecaEditRequest biblioteca) {
        return new ResponseEntity<>(this.bibliotecaService.edit(id, biblioteca), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        this.bibliotecaService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
