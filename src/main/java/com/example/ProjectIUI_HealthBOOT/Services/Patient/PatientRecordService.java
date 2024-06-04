package com.example.ProjectIUI_HealthBOOT.Services.Patient;

import com.example.ProjectIUI_HealthBOOT.Dtos.PatientRecordRequest;
import com.example.ProjectIUI_HealthBOOT.Entity.Patient.Patient;
import com.example.ProjectIUI_HealthBOOT.Entity.Patient.PatientRepository;
import com.example.ProjectIUI_HealthBOOT.Entity.PatientRecord.PatientRecord;
import com.example.ProjectIUI_HealthBOOT.Entity.PatientRecord.PatientRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PatientRecordService {

    private final PatientRecordRepository patientRecordRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public PatientRecordService(PatientRecordRepository patientRecordRepository, PatientRepository patientRepository) {
        this.patientRecordRepository = patientRecordRepository;
        this.patientRepository = patientRepository;
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

    public PatientRecord AddPatientRecord(PatientRecordRequest request) {
        PatientRecord  record = new PatientRecord();
        record.setUuid(UUID.randomUUID());
        if(request.getPatientId()!=null){
            Optional<Patient> patient = patientRepository.findById(request.getPatientId());
            if(patient.isPresent()) {
                record.setPatient(patient.get());
            }else{
                Patient p = new Patient(request.getFirstName(),request.getLastName(),request.getPesel());
                p = patientRepository.save(p);
                record.setPatient(p);
            }
        }else {
            Patient p = new Patient(request.getFirstName(),request.getLastName(),request.getPesel());
            p = patientRepository.save(p);
            record.setPatient(p);
        }



        record.setPatientStatus(request.getState());
        record.setRoundDate(request.getRoundDate());
        record.setRoundTime(request.getRoundTime());
        record.setAudioFile(request.getAudioFile());
        return patientRecordRepository.save(record);
    }

}
