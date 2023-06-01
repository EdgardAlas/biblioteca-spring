package com.ues.edu.sv.controllers;

import com.ues.edu.sv.dto.libros.crear.LibroSaveRequest;
import com.ues.edu.sv.dto.libros.editar.LibroEditRequest;
import com.ues.edu.sv.dto.libros.listar.LibrosResponse;
import com.ues.edu.sv.services.LibroService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin({"http://localhost:3000", "http://localhost:4200"})
@RequestMapping("/libros")
public class LibroController {
    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }


    @GetMapping
    public ResponseEntity<Page<LibrosResponse>> findAll(Pageable pageable) {
        return new ResponseEntity<>(this.libroService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibrosResponse> findById(@PathVariable int id) {
        return new ResponseEntity<>(this.libroService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LibrosResponse> save(@Valid @RequestBody LibroSaveRequest libro) {
        return new ResponseEntity<>(this.libroService.save(libro), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LibrosResponse> edit(@PathVariable int id, @Valid @RequestBody LibroEditRequest libro) {
        return new ResponseEntity<>(this.libroService.edit(id, libro), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        this.libroService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
