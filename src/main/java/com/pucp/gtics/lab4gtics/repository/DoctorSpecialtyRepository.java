package com.pucp.gtics.lab4gtics.repository;

import com.pucp.gtics.lab4gtics.entity.DoctorSpecialty;
import com.pucp.gtics.lab4gtics.entity.DoctorSpecialtyId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorSpecialtyRepository extends JpaRepository<DoctorSpecialty, DoctorSpecialtyId> {
}