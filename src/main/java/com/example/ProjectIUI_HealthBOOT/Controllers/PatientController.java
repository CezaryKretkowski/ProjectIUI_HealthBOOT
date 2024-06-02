package com.example.ProjectIUI_HealthBOOT.Controllers;

import com.example.ProjectIUI_HealthBOOT.Dtos.PatientCreateRequest;
import com.example.ProjectIUI_HealthBOOT.Dtos.PatientResponse;
import com.example.ProjectIUI_HealthBOOT.Entity.Patient.Patient;
import com.example.ProjectIUI_HealthBOOT.Services.Patient.PatientService;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
        return new PatientResponse("ok",patientList);
    }

    @GetMapping("/{id}")
    public PatientResponse getPatientById(@PathVariable UUID id) {
        Patient patient=patientService.getPatientById(id);
        List<Patient> patientList= new ArrayList<>();
        patientList.add(patient);
        return new PatientResponse("ok",patientList);
    }

    @GetMapping("/{pesel}")
    public PatientResponse getPatientByPesel(@PathVariable String pesel) {
        Patient patient = patientService.getPatientByPesel(pesel);
        return new PatientResponse("ok", (List<Patient>) patient);
    }

    @PostMapping("/add")
    public PatientResponse addPatient(@RequestBody PatientCreateRequest patientCreateRequest) throws BadRequestException {
        Patient patient = new Patient(patientCreateRequest);
        try {
            Patient newPatient = patientService.addPatient(patient);
            List<Patient> patientList= new ArrayList<>();
            patientList.add(patient);
            return new PatientResponse("ok",patientList);
        }catch (Exception e){
            throw new ResponseStatusException( 409, "Sql Exception", e);
        }
    }

    @PutMapping("/update/{id}")
    public PatientResponse updatePatient(@PathVariable UUID id, @RequestBody Patient patient) {
        Patient newPatient=patientService.updatePatient(id, patient);
        List<Patient> patientList= new ArrayList<>();
        patientList.add(patient);
        return new PatientResponse("ok",patientList);
    }

    @DeleteMapping("/del/{id}")
    public void deletePatient(@PathVariable UUID id) {
        patientService.deletePatient(id);
    }
}