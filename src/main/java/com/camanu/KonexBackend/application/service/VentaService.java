package com.camanu.KonexBackend.application.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.camanu.KonexBackend.application.port.in.ventas.CreateVentaUseCase;
import com.camanu.KonexBackend.application.port.in.ventas.DeleteVentaUseCase;
import com.camanu.KonexBackend.application.port.in.ventas.GetVentaUseCase;
import com.camanu.KonexBackend.application.port.in.ventas.UpdateVentaUseCase;
import com.camanu.KonexBackend.application.port.out.VentaRepositoryPort;
import com.camanu.KonexBackend.domain.model.Venta;

public class VentaService implements
        CreateVentaUseCase,
        GetVentaUseCase,
        UpdateVentaUseCase,
        DeleteVentaUseCase {

    private final VentaRepositoryPort repository;

    public VentaService(VentaRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Venta create(String fechaHoraIso, String medicamentoId, int cantidad, double valorUnitario) {
        LocalDateTime fechaHora = LocalDateTime.parse(fechaHoraIso);
        double valorTotal = cantidad * valorUnitario;

        Venta venta = Venta.create(
                fechaHora,
                medicamentoId,
                cantidad,
                valorUnitario,
                valorTotal
        );

        return repository.save(venta);
    }

    @Override
    public Optional<Venta> getById(String id) {
        return repository.findById(id);
    }

    @Override
    public List<Venta> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Venta> update(String id,
                                  String fechaHoraIso,
                                  String medicamentoId,
                                  Integer cantidad,
                                  Double valorUnitario) {

        Optional<Venta> existing = repository.findById(id);
        if (existing.isEmpty()) {
            return Optional.empty();
        }

        Venta base = existing.get();

        LocalDateTime nuevaFechaHora =
                fechaHoraIso != null ? LocalDateTime.parse(fechaHoraIso) : base.getFechaHora();

        String nuevoMedicamento =
                medicamentoId != null ? medicamentoId : base.getMedicamentoId();

        int nuevaCantidad =
                cantidad != null ? cantidad : base.getCantidad();

        double nuevoValorUnitario =
                valorUnitario != null ? valorUnitario : base.getValorUnitario();

        double nuevoValorTotal = nuevaCantidad * nuevoValorUnitario;

        Venta updated = base.withUpdatedData(
                nuevaFechaHora,
                nuevoMedicamento,
                nuevaCantidad,
                nuevoValorUnitario,
                nuevoValorTotal
        );

        Venta saved = repository.save(updated);
        return Optional.of(saved);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}