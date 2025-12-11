package com.camanu.KonexBackend.application.port.out;

import java.util.List;
import java.util.Optional;

import com.camanu.KonexBackend.domain.model.Venta;

public interface VentaRepositoryPort {
    Venta save(Venta venta);
    Optional<Venta> findById(String id);
    List<Venta> findAll();
    void deleteById(String id);
    boolean existsById(String id);
}