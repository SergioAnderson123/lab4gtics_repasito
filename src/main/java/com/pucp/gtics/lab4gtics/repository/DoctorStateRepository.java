package com.pucp.gtics.lab4gtics.repository;

import com.pucp.gtics.lab4gtics.entity.DoctorState;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorStateRepository extends JpaRepository<DoctorState, Integer> {
}