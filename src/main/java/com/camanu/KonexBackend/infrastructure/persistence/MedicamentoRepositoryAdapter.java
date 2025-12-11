package com.camanu.KonexBackend.infrastructure.persistence;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.camanu.KonexBackend.application.port.out.MedicamentoRepositoryPort;
import com.camanu.KonexBackend.domain.model.Medicamento;

@Component
public class MedicamentoRepositoryAdapter implements MedicamentoRepositoryPort {

    private final SpringDataMedicamentoRepository repository;

    public MedicamentoRepositoryAdapter(SpringDataMedicamentoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Medicamento save(Medicamento medicamento) {
        MedicamentoEntity entity = toEntity(medicamento);
        MedicamentoEntity saved = repository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<Medicamento> findById(String id) {
        return repository.findById(id).map(this::toDomain);
    }

    @Override
    public List<Medicamento> findAll() {
        return repository.findAll().stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsById(String id) {
        return repository.existsById(id);
    }

    private MedicamentoEntity toEntity(Medicamento m) {
        return new MedicamentoEntity(
                m.getId(),
                m.getNombre(),
                m.getLaboratorio(),
                m.getFechaFabricacion(),
                m.getFechaVencimiento(),
                m.getCantidadEnStock(),
                m.getValorUnitario()
        );
    }

    private Medicamento toDomain(MedicamentoEntity e) {
        return new Medicamento(
                e.getId(),
                e.getNombre(),
                e.getLaboratorio(),
                e.getFechaFabricacion(),
                e.getFechaVencimiento(),
                e.getCantidadEnStock(),
                e.getValorUnitario()
        );
    }
}