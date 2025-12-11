package com.camanu.KonexBackend.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.camanu.KonexBackend.application.port.in.medicamentos.CreateMedicamentoUseCase;
import com.camanu.KonexBackend.application.port.in.medicamentos.DeleteMedicamentoUseCase;
import com.camanu.KonexBackend.application.port.in.medicamentos.GetMedicamentoUseCase;
import com.camanu.KonexBackend.application.port.in.medicamentos.UpdateMedicamentoUseCase;
import com.camanu.KonexBackend.application.service.MedicamentoService;
import com.camanu.KonexBackend.domain.model.Medicamento;
import com.camanu.KonexBackend.web.dto.medicamento.CrearMedicamentoRequest;
import com.camanu.KonexBackend.web.dto.medicamento.MedicamentoResponse;
import com.camanu.KonexBackend.web.dto.medicamento.UpdateMedicamentoRequest;

@RestController
@RequestMapping("/api/medicamentos")
public class MedicamentoController {

    private final CreateMedicamentoUseCase createUseCase;
    private final GetMedicamentoUseCase getUseCase;
    private final UpdateMedicamentoUseCase updateUseCase;
    private final DeleteMedicamentoUseCase deleteUseCase;

    public MedicamentoController(MedicamentoService service) {
        this.createUseCase = service;
        this.getUseCase = service;
        this.updateUseCase = service;
        this.deleteUseCase = service;
    }

    @PostMapping
    public ResponseEntity<MedicamentoResponse> create(@RequestBody CrearMedicamentoRequest req) {
        Medicamento saved = createUseCase.create(
                req.nombre,
                req.laboratorio,
                req.fechaFabricacion,
                req.fechaVencimiento,
                req.cantidadEnStock,
                req.valorUnitario
        );
        return ResponseEntity.status(201).body(toResponse(saved));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicamentoResponse> getById(@PathVariable String id) {
        return getUseCase.getById(id)
                .map(m -> ResponseEntity.ok(toResponse(m)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<MedicamentoResponse>> getAll() {
        List<MedicamentoResponse> list = getUseCase.getAll().stream().map(this::toResponse).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicamentoResponse> update(@PathVariable String id, @RequestBody UpdateMedicamentoRequest req) {
        // se permiten nulls en req para manejo parcial; convertimos valores primitivos por defecto
        int cantidad = req.cantidadEnStock != null ? req.cantidadEnStock : 0;
        double valor = req.valorUnitario != null ? req.valorUnitario : 0.0;

        return updateUseCase.update(
                id,
                req.nombre,
                req.laboratorio,
                req.fechaFabricacion,
                req.fechaVencimiento,
                cantidad,
                valor
        ).map(this::toResponse)
         .map(ResponseEntity::ok)
         .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        if (!getUseCase.getById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        deleteUseCase.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private MedicamentoResponse toResponse(Medicamento m) {
        MedicamentoResponse r = new MedicamentoResponse();
        r.id = m.getId();
        r.nombre = m.getNombre();
        r.laboratorio = m.getLaboratorio();
        r.fechaFabricacion = m.getFechaFabricacion().toString(); // yyyy-MM-dd
        r.fechaVencimiento = m.getFechaVencimiento().toString();
        r.cantidadEnStock = m.getCantidadEnStock();
        r.valorUnitario = m.getValorUnitario();
        return r;
    }
}