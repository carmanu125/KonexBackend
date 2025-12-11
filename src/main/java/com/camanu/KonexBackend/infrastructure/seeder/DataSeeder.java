package com.camanu.KonexBackend.infrastructure.seeder;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.camanu.KonexBackend.application.service.MedicamentoService;
import com.camanu.KonexBackend.application.service.VentaService;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(MedicamentoService medicamentoService, VentaService ventaService) {
        return args -> {

            // Semilla para Medicamentos
            medicamentoService.create("Paracetamol 500mg", "LabFarma", "2025-01-01", "2026-01-01", 100, 2.5);
            medicamentoService.create("Ibuprofeno 400mg", "BioLab", "2024-06-01", "2025-06-01", 50, 3.0);
            medicamentoService.create("Loratadina", "Farmacol", "2025-02-15", "2026-02-15", 200, 1.8);
            medicamentoService.create("Acetaminofen 250mg", "Farmacol", "2025-02-15", "2026-02-15", 200, 1.8);
            medicamentoService.create("Acetaminofen 500mg", "Farmacol", "2025-02-15", "2026-02-15", 200, 1.8);
            medicamentoService.create("Cefalexina 250mg", "Farmacol", "2025-02-15", "2026-02-15", 200, 1.8);
            medicamentoService.create("Cefalexina 500mg", "Farmacol", "2025-02-15", "2026-02-15", 200, 1.8);
            medicamentoService.create("Azitromicina 250mg", "Farmacol", "2025-02-15", "2026-02-15", 200, 1.8);
            medicamentoService.create("Azitromicina 500mg", "Farmacol", "2025-02-15", "2026-02-15", 200, 1.8);
            medicamentoService.create("Amoxicilina 250mg", "Farmacol", "2025-02-15", "2026-02-15", 200, 1.8);

            ventaService.create("2025-12-11T10:00", "1", 5, 2.5); // id de medicamento, cantidad, valorUnitario
            ventaService.create("2025-12-11T11:00", "2", 2, 3.0);

        };
    }
}