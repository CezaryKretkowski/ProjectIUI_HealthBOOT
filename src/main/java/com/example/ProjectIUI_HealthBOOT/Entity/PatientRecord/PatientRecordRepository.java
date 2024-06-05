package com.example.ProjectIUI_HealthBOOT.Entity.PatientRecord;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PatientRecordRepository extends JpaRepository<PatientRecord, UUID> {
    Optional<PatientRecord> findByPatientId(UUID patient);
}
