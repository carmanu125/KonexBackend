package com.camanu.KonexBackend.application.port.in.medicamentos;

import java.util.List;
import java.util.Optional;

import com.camanu.KonexBackend.domain.model.Medicamento;

public interface GetMedicamentoUseCase {
    Optional<Medicamento> getById(String id);
    List<Medicamento> getAll();
}