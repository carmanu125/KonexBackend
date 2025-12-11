package com.camanu.KonexBackend.web.dto.medicamento;

public class UpdateMedicamentoRequest {

    // Para actualización parcial acepta null
    public String nombre;
    public String laboratorio;
    public String fechaFabricacion; // yyyy-MM-dd
    public String fechaVencimiento; // yyyy-MM-dd
    public Integer cantidadEnStock; // puede ser null
    public Double valorUnitario;    // puede ser null
}