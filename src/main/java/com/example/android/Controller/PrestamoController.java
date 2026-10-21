package com.example.android.Controller;


import com.example.android.DTO.PrestamoRequest;
import com.example.android.Model.Prestamo;
import com.example.android.Service.PrestamoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
@CrossOrigin(origins = "*")
public class PrestamoController {

    @Autowired
    private PrestamoService service;

    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody PrestamoRequest request) {
        try {
            Prestamo prestamo = service.registrarPrestamo(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(prestamo);
        } catch (IllegalArgumentException e) {
            // Retorna un 400 Bad Request si falla alguna regla de negocio
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Prestamo>> consultarTodos() {
        return ResponseEntity.ok(service.consultarTodos());
    }

    @GetMapping("/herramienta/{id}")
    public ResponseEntity<List<Prestamo>> consultarPorHerramienta(@PathVariable Long id) {
        return ResponseEntity.ok(service.consultarPorHerramienta(id));
    }
}