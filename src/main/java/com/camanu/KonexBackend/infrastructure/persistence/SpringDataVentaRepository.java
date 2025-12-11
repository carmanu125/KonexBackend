package com.camanu.KonexBackend.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataVentaRepository extends JpaRepository<VentaEntity, String> {
}