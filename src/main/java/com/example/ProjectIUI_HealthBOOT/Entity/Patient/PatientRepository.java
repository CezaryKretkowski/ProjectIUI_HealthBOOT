package com.example.ProjectIUI_HealthBOOT.Entity.Patient;

import com.example.ProjectIUI_HealthBOOT.Entity.Patient.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {
}