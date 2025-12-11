package com.camanu.KonexBackend.infrastructure.persistence;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ventas")
public class VentaEntity {

    @Id
    private String id;

    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    @Column(name = "medicamento_id", nullable = false)
    private String medicamentoId;

    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    @Column(name = "valor_unitario", nullable = false)
    private double valorUnitario;

    @Column(name = "valor_total", nullable = false)
    private double valorTotal;

    public VentaEntity() {}

    public VentaEntity(String id, LocalDateTime fechaHora, String medicamentoId, int cantidad, double valorUnitario, double valorTotal) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.medicamentoId = medicamentoId;
        this.cantidad = cantidad;
        this.valorUnitario = valorUnitario;
        this.valorTotal = valorTotal;
    }

    // getters y setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }

    public String getMedicamentoId() { return medicamentoId; }
    public void setMedicamentoId(String medicamentoId) { this.medicamentoId = medicamentoId; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public double getValorUnitario() { return valorUnitario; }
    public void setValorUnitario(double valorUnitario) { this.valorUnitario = valorUnitario; }

    public double getValorTotal() { return valorTotal; }
    public void setValorTotal(double valorTotal) { this.valorTotal = valorTotal; }
}