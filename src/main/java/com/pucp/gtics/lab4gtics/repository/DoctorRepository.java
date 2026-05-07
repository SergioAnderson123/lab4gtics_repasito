package com.pucp.gtics.lab4gtics.repository;

import com.pucp.gtics.lab4gtics.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
}