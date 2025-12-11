package com.camanu.KonexBackend.application.port.in.ventas;

import java.util.List;
import java.util.Optional;

import com.camanu.KonexBackend.domain.model.Venta;

public interface GetVentaUseCase {
    Optional<Venta> getById(String id);
    List<Venta> getAll();
}