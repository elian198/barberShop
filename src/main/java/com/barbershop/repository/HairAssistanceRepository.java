package com.barbershop.repository;

import com.barbershop.entites.HairAssistance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HairAssistanceRepository extends JpaRepository<HairAssistance, Long> {
}
