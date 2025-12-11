package com.camanu.KonexBackend.application.port.out;

import java.util.Optional;
import java.util.List;

import com.camanu.KonexBackend.domain.model.Medicamento;

public interface MedicamentoRepositoryPort {

    Medicamento save(Medicamento medicamento);

    Optional<Medicamento> findById(String id);

    List<Medicamento> findAll();

    void deleteById(String id);

    boolean existsById(String id);
}
