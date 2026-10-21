package com.example.android.controller;


import com.example.android.model.Herramienta;
import com.example.android.service.HerramientaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/herramientas")
@CrossOrigin(origins = "*") // Permite peticiones desde el frontend (Android/Retrofit)
public class Herramientacontroller {

    @Autowired
    private HerramientaService service;

    @PostMapping
    public ResponseEntity<Herramienta> registrar(@Valid @RequestBody Herramienta herramienta) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registrarHerramienta(herramienta));
    }

    @GetMapping
    public ResponseEntity<List<Herramienta>> consultar() {
        return ResponseEntity.ok(service.consultarHerramientas());
    }
}