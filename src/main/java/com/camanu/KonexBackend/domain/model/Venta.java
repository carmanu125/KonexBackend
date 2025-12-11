package com.camanu.KonexBackend.domain.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Venta {

    private final String id;
    private final LocalDateTime fechaHora;
    private final String medicamentoId;
    private final int cantidad;
    private final double valorUnitario;
    private final double valorTotal;

    public Venta(String id,
                 LocalDateTime fechaHora,
                 String medicamentoId,
                 int cantidad,
                 double valorUnitario,
                 double valorTotal) {
        this.id = Objects.requireNonNull(id, "id no puede ser null");
        this.fechaHora = Objects.requireNonNull(fechaHora, "fechaHora no puede ser null");
        this.medicamentoId = Objects.requireNonNull(medicamentoId, "medicamentoId no puede ser null");
        this.cantidad = validateCantidad(cantidad);
        this.valorUnitario = validateValorUnitario(valorUnitario);
        this.valorTotal = validateValorTotal(valorTotal, this.cantidad, this.valorUnitario);
    }

    public static Venta create(LocalDateTime fechaHora,
                               String medicamentoId,
                               int cantidad,
                               double valorUnitario,
                               double valorTotal) {
        return new Venta(UUID.randomUUID().toString(), fechaHora, medicamentoId, cantidad, valorUnitario, valorTotal);
    }

    private static int validateCantidad(int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("Cantidad debe ser mayor que 0");
        }
        return cantidad;
    }

    private static double validateValorUnitario(double valorUnitario) {
        if (valorUnitario <= 0) {
            throw new IllegalArgumentException("Valor unitario debe ser mayor que 0");
        }
        return valorUnitario;
    }

    private static double validateValorTotal(double valorTotal, int cantidad, double valorUnitario) {
        double expected = cantidad * valorUnitario;
        if (Math.abs(expected - valorTotal) > 0.0001) {
            throw new IllegalArgumentException("Valor total no coincide con cantidad * valorUnitario");
        }
        return valorTotal;
    }

    // getters
    public String getId() { return id; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public String getMedicamentoId() { return medicamentoId; }
    public int getCantidad() { return cantidad; }
    public double getValorUnitario() { return valorUnitario; }
    public double getValorTotal() { return valorTotal; }

    public Venta withUpdatedData(LocalDateTime fechaHora,
                                 String medicamentoId,
                                 int cantidad,
                                 double valorUnitario,
                                 double valorTotal) {
        return new Venta(this.id,
                fechaHora != null ? fechaHora : this.fechaHora,
                medicamentoId != null ? medicamentoId : this.medicamentoId,
                cantidad,
                valorUnitario,
                valorTotal);
    }
}