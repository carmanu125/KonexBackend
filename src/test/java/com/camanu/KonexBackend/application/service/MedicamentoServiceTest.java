package com.camanu.KonexBackend.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import com.camanu.KonexBackend.application.port.out.MedicamentoRepositoryPort;
import com.camanu.KonexBackend.domain.model.Medicamento;

public class MedicamentoServiceTest {

    private MedicamentoRepositoryPort repository;
    private MedicamentoService service;

    @BeforeEach
    void setUp() {
        repository = mock(MedicamentoRepositoryPort.class);
        service = new MedicamentoService(repository);
    }

    @Test
    void crearMedicamento_debeGuardarYRetornarMedicamento() {
        
        String nombre = "Ibuprofeno 400mg";
        String laboratorio = "Genfar";
        String fechaFabricacion = "2024-01-01";
        String fechaVencimiento = "2026-01-01";
        int cantidad = 50;
        double valorUnitario = 3.0;

        // Simulamos respuesta del repositorio
        Medicamento medicamentoSimulado = Medicamento.create(
                nombre,
                laboratorio,
                LocalDate.parse(fechaFabricacion),
                LocalDate.parse(fechaVencimiento),
                cantidad,
                valorUnitario
        );

        when(repository.save(any(Medicamento.class)))
                .thenReturn(medicamentoSimulado);

        
        Medicamento result = service.create(
                nombre,
                laboratorio,
                fechaFabricacion,
                fechaVencimiento,
                cantidad,
                valorUnitario
        );

        
        assertNotNull(result);
        assertEquals(nombre, result.getNombre());
        assertEquals(laboratorio, result.getLaboratorio());
        assertEquals(cantidad, result.getCantidadEnStock());

        // Verificar que el repositorio recibió el medicamento
        ArgumentCaptor<Medicamento> captor = ArgumentCaptor.forClass(Medicamento.class);
        verify(repository, times(1)).save(captor.capture());

        Medicamento enviadoAlRepositorio = captor.getValue();

        assertEquals(nombre, enviadoAlRepositorio.getNombre());
        assertEquals(laboratorio, enviadoAlRepositorio.getLaboratorio());
    }
    
}
