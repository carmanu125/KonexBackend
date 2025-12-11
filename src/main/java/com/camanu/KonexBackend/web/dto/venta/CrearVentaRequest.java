package com.camanu.KonexBackend.web.dto.venta;

public class CrearVentaRequest {

    public String fechaHora; // formato ISO: yyyy-MM-ddTHH:mm:ss

    public String medicamentoId;

    public int cantidad;

    public double valorUnitario;
}