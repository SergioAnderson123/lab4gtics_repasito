package com.pucp.gtics.lab4gtics.repository;

import com.pucp.gtics.lab4gtics.entity.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialtyRepository extends JpaRepository<Specialty, Integer> {
}