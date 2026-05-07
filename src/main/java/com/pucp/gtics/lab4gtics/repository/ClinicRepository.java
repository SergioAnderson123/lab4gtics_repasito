package com.pucp.gtics.lab4gtics.repository;

import com.pucp.gtics.lab4gtics.entity.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicRepository extends JpaRepository<Clinic, Integer> {
}