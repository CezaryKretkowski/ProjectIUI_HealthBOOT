package com.example.ProjectIUI_HealthBOOT.Services.Patient;

import com.example.ProjectIUI_HealthBOOT.Entity.Patient.Patient;
import com.example.ProjectIUI_HealthBOOT.Entity.Patient.PatientRepository;
import com.example.ProjectIUI_HealthBOOT.Entity.PatientRecord.PatientRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientRecordRepository patientRecordRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository, PatientRecordRepository patientRecordRepository) {
        this.patientRepository = patientRepository;
        this.patientRecordRepository = patientRecordRepository;
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatientById(UUID id) {
        return patientRepository.findById(id).orElse(null);
    }
    public Patient getPatientByPesel(String pesel) {
        return patientRepository.findByPesel(pesel).orElse(null);
    }

    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient updatePatient(UUID id, Patient updatedPatient) {
        return patientRepository.findById(id).map(patient -> {
            patient.setFirstName(updatedPatient.getFirstName());
            patient.setLastName(updatedPatient.getLastName());
            patient.setPesel(updatedPatient.getPesel());
            return patientRepository.save(patient);
        }).orElse(null);
    }

    public void deletePatient(UUID id) {
        var patientRecords = patientRecordRepository.findPatientRecordsByPatient_Uuid(id);
        patientRecords.forEach(patientRecordRepository::delete);
        patientRepository.deleteById(id);
    }
}
