package com.example.ProjectIUI_HealthBOOT.Services.Patient;

import com.example.ProjectIUI_HealthBOOT.Entity.PatientRecord.PatientRecord;
import com.example.ProjectIUI_HealthBOOT.Entity.PatientRecord.PatientRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PatientRecordService {

    private final PatientRecordRepository patientRecordRepository;

    @Autowired
    public PatientRecordService(PatientRecordRepository patientRecordRepository) {
        this.patientRecordRepository = patientRecordRepository;
    }

    public List<PatientRecord> getAllPatientRecords() {
        return patientRecordRepository.findAll();
    }

    public PatientRecord getPatientRecordById(UUID id) {
        return patientRecordRepository.findById(id).orElse(null);
    }

    public PatientRecord addPatientRecord(PatientRecord patientRecord) {
        return patientRecordRepository.save(patientRecord);
    }

    public PatientRecord updatePatientRecord(UUID id, PatientRecord updatedPatientRecord) {
        return patientRecordRepository.findById(id).map(patientRecord -> {
            patientRecord.setPatientStatus(updatedPatientRecord.getPatientStatus());
            return patientRecordRepository.save(patientRecord);
        }).orElse(null);
    }

    public void deletePatientRecord(UUID id) {
        patientRecordRepository.deleteById(id);
    }
}
