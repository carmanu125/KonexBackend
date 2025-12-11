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

import com.camanu.KonexBackend.application.port.in.ventas.CreateVentaUseCase;
import com.camanu.KonexBackend.application.port.in.ventas.DeleteVentaUseCase;
import com.camanu.KonexBackend.application.port.in.ventas.GetVentaUseCase;
import com.camanu.KonexBackend.application.port.in.ventas.UpdateVentaUseCase;
import com.camanu.KonexBackend.application.service.VentaService;
import com.camanu.KonexBackend.domain.model.Venta;
import com.camanu.KonexBackend.web.dto.venta.CrearVentaRequest;
import com.camanu.KonexBackend.web.dto.venta.UpdateVentaRequest;
import com.camanu.KonexBackend.web.dto.venta.VentaResponse;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    private final CreateVentaUseCase createUseCase;
    private final GetVentaUseCase getUseCase;
    private final UpdateVentaUseCase updateUseCase;
    private final DeleteVentaUseCase deleteUseCase;

    public VentaController(VentaService service) {
        this.createUseCase = service;
        this.getUseCase = service;
        this.updateUseCase = service;
        this.deleteUseCase = service;
    }

    @PostMapping
    public ResponseEntity<VentaResponse> create(@RequestBody CrearVentaRequest req) {
        Venta saved = createUseCase.create(
                req.fechaHora,
                req.medicamentoId,
                req.cantidad,
                req.valorUnitario
        );
        return ResponseEntity.status(201).body(toResponse(saved));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaResponse> getById(@PathVariable String id) {
        return getUseCase.getById(id)
                .map(v -> ResponseEntity.ok(toResponse(v)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<VentaResponse>> getAll() {
        List<VentaResponse> list = getUseCase.getAll().stream().map(this::toResponse).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VentaResponse> update(@PathVariable String id, @RequestBody UpdateVentaRequest req) {
        Integer cantidad = req.cantidad != null ? req.cantidad : null;
        Double valor = req.valorUnitario != null ? req.valorUnitario : null;

        return updateUseCase.update(
                id,
                req.fechaHora,
                req.medicamentoId,
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

    private VentaResponse toResponse(Venta v) {
        VentaResponse r = new VentaResponse();
        r.id = v.getId();
        r.fechaHora = v.getFechaHora().toString(); // ISO
        r.medicamentoId = v.getMedicamentoId();
        r.cantidad = v.getCantidad();
        r.valorUnitario = v.getValorUnitario();
        r.valorTotal = v.getValorTotal();
        return r;
    }
}