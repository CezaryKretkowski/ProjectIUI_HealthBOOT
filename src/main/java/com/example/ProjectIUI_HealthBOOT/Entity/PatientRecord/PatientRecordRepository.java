package com.example.ProjectIUI_HealthBOOT.Entity.PatientRecord;

import com.example.ProjectIUI_HealthBOOT.Entity.Patient.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PatientRecordRepository extends JpaRepository<PatientRecord, UUID> {
    List<PatientRecord> findPatientRecordsByPatient_Uuid(UUID uuid);
}
