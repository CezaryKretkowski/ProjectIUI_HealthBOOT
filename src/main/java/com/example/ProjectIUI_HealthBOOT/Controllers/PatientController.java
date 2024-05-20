package com.example.ProjectIUI_HealthBOOT.Controllers;

import com.example.ProjectIUI_HealthBOOT.Dtos.PatientResponse;
import com.example.ProjectIUI_HealthBOOT.Entity.Patient.Patient;
import com.example.ProjectIUI_HealthBOOT.Services.Patient.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/all")
    public PatientResponse getAllPatients() {
        List<Patient> patientList = patientService.getAllPatients();
        /*for(Patient p:patientList){
            p.setUuid(null);
        }*/
        return new PatientResponse("ok",patientList);
    }

    @GetMapping("/{id}")
    public PatientResponse getPatientById(@PathVariable UUID id) {
        Patient patient=patientService.getPatientById(id);
       // patient.setUuid(null);
        List<Patient> patientList= new ArrayList<>();
        patientList.add(patient);
        return new PatientResponse("ok",patientList);
    }

    @PostMapping("/add")
    public PatientResponse addPatient(@RequestBody Patient patient) {
        Patient newPatient=patientService.addPatient(patient);
        //newPatient.setUuid(null);
        List<Patient> patientList= new ArrayList<>();
        patientList.add(patient);
        return new PatientResponse("ok",patientList);
    }

    @PutMapping("/update/{id}")
    public PatientResponse updatePatient(@PathVariable UUID id, @RequestBody Patient patient) {
        Patient newPatient=patientService.updatePatient(id, patient);
        //newPatient.setUuid(null);
        List<Patient> patientList= new ArrayList<>();
        patientList.add(patient);
        return new PatientResponse("ok",patientList);
    }

    @DeleteMapping("/del/{id}")
    public void deletePatient(@PathVariable UUID id) {
        patientService.deletePatient(id);
    }
}