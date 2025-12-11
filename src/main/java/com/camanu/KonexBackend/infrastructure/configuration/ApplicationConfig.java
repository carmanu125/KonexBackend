package com.camanu.KonexBackend.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.camanu.KonexBackend.application.port.out.MedicamentoRepositoryPort;
import com.camanu.KonexBackend.application.port.out.VentaRepositoryPort;
import com.camanu.KonexBackend.application.service.MedicamentoService;
import com.camanu.KonexBackend.application.service.VentaService;

@Configuration
public class ApplicationConfig {

    @Bean
    public MedicamentoService medicamentoService(MedicamentoRepositoryPort repo) {
        return new MedicamentoService(repo);
    }
    
    @Bean
    public VentaService ventaService(VentaRepositoryPort repo) {
        return new VentaService(repo);
    }
}
