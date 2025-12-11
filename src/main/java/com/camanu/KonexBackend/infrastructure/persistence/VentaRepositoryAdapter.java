package com.camanu.KonexBackend.infrastructure.persistence;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.camanu.KonexBackend.application.port.out.VentaRepositoryPort;
import com.camanu.KonexBackend.domain.model.Venta;

@Component
public class VentaRepositoryAdapter implements VentaRepositoryPort {

    private final SpringDataVentaRepository repository;

    public VentaRepositoryAdapter(SpringDataVentaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Venta save(Venta venta) {
        VentaEntity entity = toEntity(venta);
        VentaEntity saved = repository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<Venta> findById(String id) {
        return repository.findById(id).map(this::toDomain);
    }

    @Override
    public List<Venta> findAll() {
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

    private VentaEntity toEntity(Venta v) {
        return new VentaEntity(
                v.getId(),
                v.getFechaHora(),
                v.getMedicamentoId(),
                v.getCantidad(),
                v.getValorUnitario(),
                v.getValorTotal()
        );
    }

    private Venta toDomain(VentaEntity e) {
        return new Venta(
                e.getId(),
                e.getFechaHora(),
                e.getMedicamentoId(),
                e.getCantidad(),
                e.getValorUnitario(),
                e.getValorTotal()
        );
    }
}