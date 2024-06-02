package com.example.ProjectIUI_HealthBOOT.Services.Patient;

import com.example.ProjectIUI_HealthBOOT.Entity.Patient.Patient;
import com.example.ProjectIUI_HealthBOOT.Entity.Patient.PatientRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.UUID;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
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

    public Patient addPatient(Patient patient) throws Exception {
        try {
            return patientRepository.save(patient);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
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
        patientRepository.deleteById(id);
    }
}
