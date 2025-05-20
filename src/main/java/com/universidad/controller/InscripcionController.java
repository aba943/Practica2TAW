package com.universidad.controller;

import com.universidad.dto.InscripcionDTO;
import com.universidad.service.IInscripcionService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inscripciones")
public class InscripcionController {

    private final IInscripcionService inscripcionService;

    @Autowired
    public InscripcionController(IInscripcionService inscripcionService) {
        this.inscripcionService = inscripcionService;
    }

    @PostMapping
    public ResponseEntity<InscripcionDTO> crearInscripcion(@Valid @RequestBody InscripcionDTO inscripcionDTO) {
        InscripcionDTO creada = inscripcionService.crearInscripcion(inscripcionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @GetMapping
    public ResponseEntity<List<InscripcionDTO>> listarInscripciones() {
        return ResponseEntity.ok(inscripcionService.listarInscripciones());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InscripcionDTO> obtenerInscripcion(@PathVariable Long id) {
        InscripcionDTO inscripcion = inscripcionService.obtenerInscripcion(id);
        return ResponseEntity.ok(inscripcion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarInscripcion(@PathVariable Long id) {
        inscripcionService.eliminarInscripcion(id);
        return ResponseEntity.noContent().build();
    }
}
