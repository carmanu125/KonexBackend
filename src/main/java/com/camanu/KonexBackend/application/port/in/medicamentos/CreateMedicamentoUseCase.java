package com.camanu.KonexBackend.application.port.in.medicamentos;

import com.camanu.KonexBackend.domain.model.Medicamento;

public interface CreateMedicamentoUseCase {
    Medicamento create(String nombre,
                       String laboratorio,
                       String fechaFabricacionIso,  // yyyy-MM-dd
                       String fechaVencimientoIso,
                       int cantidadEnStock,
                       double valorUnitario);
}