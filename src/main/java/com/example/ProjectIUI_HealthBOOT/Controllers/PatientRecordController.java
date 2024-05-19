package com.example.ProjectIUI_HealthBOOT.Controllers;

import com.example.ProjectIUI_HealthBOOT.Entity.PatientRecord.PatientRecord;
import com.example.ProjectIUI_HealthBOOT.Services.Patient.PatientRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/records")
public class PatientRecordController {

    private final PatientRecordService patientRecordService;

    @Autowired
    public PatientRecordController(PatientRecordService patientRecordService) {
        this.patientRecordService = patientRecordService;
    }

    @GetMapping("/all")
    public List<PatientRecord> getAllPatientRecords() {
        return patientRecordService.getAllPatientRecords();
    }

    @GetMapping("/{id}")
    public PatientRecord getPatientRecordById(@PathVariable UUID id) {
        return patientRecordService.getPatientRecordById(id);
    }

    @PostMapping("/add")
    public PatientRecord addPatientRecord(@RequestBody PatientRecord patientRecord) {
        return patientRecordService.addPatientRecord(patientRecord);
    }

    @PutMapping("/update/{id}")
    public PatientRecord updatePatientRecord(@PathVariable UUID id, @RequestBody PatientRecord patientRecord) {
        return patientRecordService.updatePatientRecord(id, patientRecord);
    }

    @DeleteMapping("/del/{id}")
    public void deletePatientRecord(@PathVariable UUID id) {
        patientRecordService.deletePatientRecord(id);
    }
}









