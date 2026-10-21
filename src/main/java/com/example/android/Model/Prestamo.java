package com.example.android.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "prestamos")
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El responsable es obligatorio")
    private String responsable;

    @NotNull
    @Column(name = "fecha_prestamo")
    private LocalDate fechaPrestamo;

    @NotNull
    @Min(value = 1, message = "La cantidad prestada debe ser mayor a 0")
    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name = "herramienta_id", nullable = false)
    private Herramienta herramienta;

    // Generar Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getResponsable() { return responsable; }
    public void setResponsable(String responsable) { this.responsable = responsable; }
    public LocalDate getFechaPrestamo() { return fechaPrestamo; }
    public void setFechaPrestamo(LocalDate fechaPrestamo) { this.fechaPrestamo = fechaPrestamo; }
    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
    public Herramienta getHerramienta() { return herramienta; }
    public void setHerramienta(Herramienta herramienta) { this.herramienta = herramienta; }
}