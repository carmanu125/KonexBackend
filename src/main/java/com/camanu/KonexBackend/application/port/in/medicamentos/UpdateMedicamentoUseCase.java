package com.camanu.KonexBackend.application.port.in.medicamentos;

import java.util.Optional;

import com.camanu.KonexBackend.domain.model.Medicamento;

public interface UpdateMedicamentoUseCase {
    Optional<Medicamento> update(String id,
                                String nombre,
                                String laboratorio,
                                String fechaFabricacionIso,
                                String fechaVencimientoIso,
                                int cantidadEnStock,
                                double valorUnitario);
}