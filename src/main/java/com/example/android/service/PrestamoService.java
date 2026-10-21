package com.example.android.service;


import com.example.android.dto.PrestamoRequest;
import com.example.android.model.Herramienta;
import com.example.android.model.Prestamo;
import com.example.android.repository.HerramientaRepository;
import com.example.android.repository.PrestamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class PrestamoService {

    @Autowired
    private PrestamoRepository prestamoRepository;

    @Autowired
    private HerramientaRepository herramientaRepository;

    @Transactional // Garantiza que el registro y el descuento se hagan en la misma transacción
    public Prestamo registrarPrestamo(PrestamoRequest request) {
        // 1. Comprobar que la herramienta exista
        Herramienta herramienta = herramientaRepository.findById(request.getHerramientaId())
                .orElseThrow(() -> new IllegalArgumentException("La herramienta con ID " + request.getHerramientaId() + " no existe."));

        // 2. Validar que la cantidad solicitada sea mayor a 0 y no supere las unidades disponibles
        if (request.getCantidad() <= 0) {
            throw new IllegalArgumentException("La cantidad del préstamo debe ser mayor a cero.");
        }
        if (request.getCantidad() > herramienta.getCantidad()) {
            throw new IllegalArgumentException("Unidades insuficientes. Disponibles: " + herramienta.getCantidad());
        }

        // 3. Descontar las unidades prestadas
        herramienta.setCantidad(herramienta.getCantidad() - request.getCantidad());
        herramientaRepository.save(herramienta);

        // 4. Registrar el préstamo
        Prestamo prestamo = new Prestamo();
        prestamo.setHerramienta(herramienta);
        prestamo.setResponsable(request.getResponsable());
        prestamo.setFechaPrestamo(LocalDate.now()); // Se asigna la fecha actual automáticamente
        prestamo.setCantidad(request.getCantidad());

        return prestamoRepository.save(prestamo);
    }

    public List<Prestamo> consultarTodos() {
        return prestamoRepository.findAll();
    }

    public List<Prestamo> consultarPorHerramienta(Long herramientaId) {
        return prestamoRepository.findByHerramientaId(herramientaId);
    }
}