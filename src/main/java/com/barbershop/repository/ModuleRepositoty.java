package com.barbershop.repository;

import com.barbershop.entites.Module;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuleRepositoty extends JpaRepository<Module, Long> {
}
