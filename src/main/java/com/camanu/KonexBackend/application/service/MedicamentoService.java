package com.camanu.KonexBackend.application.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.camanu.KonexBackend.application.port.in.medicamentos.CreateMedicamentoUseCase;
import com.camanu.KonexBackend.application.port.in.medicamentos.DeleteMedicamentoUseCase;
import com.camanu.KonexBackend.application.port.in.medicamentos.GetMedicamentoUseCase;
import com.camanu.KonexBackend.application.port.in.medicamentos.UpdateMedicamentoUseCase;
import com.camanu.KonexBackend.application.port.out.MedicamentoRepositoryPort;
import com.camanu.KonexBackend.domain.model.Medicamento;

public class MedicamentoService implements CreateMedicamentoUseCase,
        GetMedicamentoUseCase,
        UpdateMedicamentoUseCase,
        DeleteMedicamentoUseCase {

    private final MedicamentoRepositoryPort repository;

    public MedicamentoService(MedicamentoRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Medicamento create(String nombre, String laboratorio, String fechaFabricacionIso, String fechaVencimientoIso, int cantidadEnStock, double valorUnitario) {
        LocalDate fechaFab = LocalDate.parse(fechaFabricacionIso);
        LocalDate fechaVenc = LocalDate.parse(fechaVencimientoIso);
        Medicamento med = Medicamento.create(nombre, laboratorio, fechaFab, fechaVenc, cantidadEnStock, valorUnitario);
        return repository.save(med);
    }

    @Override
    public Optional<Medicamento> getById(String id) {
        return repository.findById(id);
    }

    @Override
    public List<Medicamento> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Medicamento> update(String id, String nombre, String laboratorio, String fechaFabricacionIso, String fechaVencimientoIso, int cantidadEnStock, double valorUnitario) {
        Optional<Medicamento> existing = repository.findById(id);
        if (existing.isEmpty()) {
            return Optional.empty();
        }
        Medicamento base = existing.get();
        LocalDate fechaFab = fechaFabricacionIso != null ? LocalDate.parse(fechaFabricacionIso) : base.getFechaFabricacion();
        LocalDate fechaVenc = fechaVencimientoIso != null ? LocalDate.parse(fechaVencimientoIso) : base.getFechaVencimiento();
        // usamos withUpdatedData para validar reglas
        Medicamento updated = base.withUpdatedData(
                nombre != null ? nombre : base.getNombre(),
                laboratorio != null ? laboratorio : base.getLaboratorio(),
                fechaFab,
                fechaVenc,
                cantidadEnStock,
                valorUnitario
        );
        Medicamento saved = repository.save(updated);
        return Optional.of(saved);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}