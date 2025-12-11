package com.camanu.KonexBackend.application.port.in.ventas;

import java.util.Optional;

import com.camanu.KonexBackend.domain.model.Venta;

public interface UpdateVentaUseCase {
    Optional<Venta> update(String id,
                           String fechaHoraIso,
                           String medicamentoId,
                           Integer cantidad,
                           Double valorUnitario);
}