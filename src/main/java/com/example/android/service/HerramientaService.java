package com.example.android.service;


import com.example.android.model.Herramienta;
import com.example.android.repository.HerramientaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HerramientaService {

    @Autowired
    private HerramientaRepository repository;

    public Herramienta registrarHerramienta(Herramienta herramienta) {
        return repository.save(herramienta);
    }

    public List<Herramienta> consultarHerramientas() {
        return repository.findAll();
    }
}