package com.example.android.Service;


import com.example.android.Model.Herramienta;
import com.example.android.Repository.HerramientaRepository;
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