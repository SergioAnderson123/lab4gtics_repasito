package com.pucp.gtics.lab4gtics.repository;

import com.pucp.gtics.lab4gtics.entity.ClinicDoctor;
import com.pucp.gtics.lab4gtics.entity.ClinicDoctorId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicDoctorRepository extends JpaRepository<ClinicDoctor, ClinicDoctorId> {
}