package com.camanu.KonexBackend.domain.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Medicamento {

    private final String id;
    private final String nombre;
    private final String laboratorio;
    private final LocalDate fechaFabricacion;
    private final LocalDate fechaVencimiento;
    private final int cantidadEnStock;
    private final double valorUnitario;

    
    public Medicamento(String id,
                       String nombre,
                       String laboratorio,
                       LocalDate fechaFabricacion,
                       LocalDate fechaVencimiento,
                       int cantidadEnStock,
                       double valorUnitario) {
        this.id = Objects.requireNonNull(id, "id no puede ser null");
        this.nombre = validateNombre(nombre);
        this.laboratorio = validateLaboratorio(laboratorio);
        this.fechaFabricacion = Objects.requireNonNull(fechaFabricacion, "fechaFabricacion no puede ser null");
        this.fechaVencimiento = Objects.requireNonNull(fechaVencimiento, "fechaVencimiento no puede ser null");
        validateFechas(this.fechaFabricacion, this.fechaVencimiento);
        this.cantidadEnStock = validateCantidad(cantidadEnStock);
        this.valorUnitario = validateValorUnitario(valorUnitario);
    }

    public static Medicamento create(String nombre,
                                     String laboratorio,
                                     LocalDate fechaFabricacion,
                                     LocalDate fechaVencimiento,
                                     int cantidadEnStock,
                                     double valorUnitario) {
        return new Medicamento(UUID.randomUUID().toString(), nombre, laboratorio, fechaFabricacion, fechaVencimiento, cantidadEnStock, valorUnitario);
    }

    private static String validateNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("Nombre no puede ser vacío");
        }
        return nombre.trim();
    }

    private static String validateLaboratorio(String laboratorio) {
        if (laboratorio == null || laboratorio.trim().isEmpty()) {
            throw new IllegalArgumentException("Laboratorio no puede ser vacío");
        }
        return laboratorio.trim();
    }

    private static void validateFechas(LocalDate fab, LocalDate venc) {
        if (!venc.isAfter(fab)) {
            throw new IllegalArgumentException("Fecha de vencimiento debe ser posterior a la fecha de fabricación");
        }
    }

    private static int validateCantidad(int cantidad) {
        if (cantidad < 0) {
            throw new IllegalArgumentException("Cantidad en stock no puede ser negativa");
        }
        return cantidad;
    }

    private static double validateValorUnitario(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor unitario debe ser mayor que cero");
        }
        return valor;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getLaboratorio() { return laboratorio; }
    public LocalDate getFechaFabricacion() { return fechaFabricacion; }
    public LocalDate getFechaVencimiento() { return fechaVencimiento; }
    public int getCantidadEnStock() { return cantidadEnStock; }
    public double getValorUnitario() { return valorUnitario; }

    public Medicamento withUpdatedData(String nombre,
                                       String laboratorio,
                                       LocalDate fechaFabricacion,
                                       LocalDate fechaVencimiento,
                                       int cantidadEnStock,
                                       double valorUnitario) {
        return new Medicamento(this.id,
                nombre != null ? nombre : this.nombre,
                laboratorio != null ? laboratorio : this.laboratorio,
                fechaFabricacion != null ? fechaFabricacion : this.fechaFabricacion,
                fechaVencimiento != null ? fechaVencimiento : this.fechaVencimiento,
                cantidadEnStock,
                valorUnitario);
    }
    
}
