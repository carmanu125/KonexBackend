package com.camanu.KonexBackend.application.port.in.ventas;

import com.camanu.KonexBackend.domain.model.Venta;

public interface CreateVentaUseCase {
    Venta create(String fechaHoraIso, String medicamentoId, int cantidad, double valorUnitario);
}