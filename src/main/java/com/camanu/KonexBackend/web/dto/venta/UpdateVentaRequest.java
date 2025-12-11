package com.camanu.KonexBackend.web.dto.venta;

public class UpdateVentaRequest {
    public String fechaHora; // yyyy-MM-ddTHH:mm:ss
    public String medicamentoId;
    public Integer cantidad;
    public Double valorUnitario;
}