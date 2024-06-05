package com.example.ProjectIUI_HealthBOOT.Controllers;

import com.example.ProjectIUI_HealthBOOT.Dtos.PatientRecordRequest;
import com.example.ProjectIUI_HealthBOOT.Dtos.PatientRecordResponse;
import com.example.ProjectIUI_HealthBOOT.Entity.PatientRecord.PatientRecord;
import com.example.ProjectIUI_HealthBOOT.Services.Patient.PatientRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public PatientRecordResponse getAllPatientRecords() {
        List<PatientRecord> patientRecordList=patientRecordService.getAllPatientRecords();
        return new PatientRecordResponse("ok",patientRecordList);
    }

    @GetMapping("/{id}")
    public PatientRecordResponse getPatientRecordById(@PathVariable UUID id) {
        List<PatientRecord> patientRecordList=new ArrayList<>();
        patientRecordList.add(patientRecordService.getPatientRecordById(id));
        return new PatientRecordResponse("ok",patientRecordList);
    }
//    @GetMapping("/patient/{id}")
//    public PatientRecordResponse getPatientRecordByPatientId(@PathVariable UUID id) {
//        List<PatientRecord> patientRecordList=new ArrayList<>();
//        //patientRecordList.add(patientRecordService.getPatientRecordByPatientId(id));
//        return new PatientRecordResponse("ok",patientRecordList);
//    }

    //@PostMapping("/add")
    public PatientRecordResponse addPatientRecord(@RequestBody PatientRecord patientRecord) {
        List<PatientRecord> patientRecordList=new ArrayList<>();
        patientRecordList.add(patientRecordService.addPatientRecord(patientRecord));
        return new PatientRecordResponse("ok",patientRecordList);
    }

    @PostMapping("/add")
    public PatientRecordResponse addPatientRecordByPatientID(@RequestBody PatientRecordRequest request) {
        patientRecordService.AddPatientRecord(request);
        return new PatientRecordResponse("ok",new ArrayList<PatientRecord>());
    }

    @PutMapping("/update/{id}")
    public PatientRecordResponse updatePatientRecord(@PathVariable UUID id, @RequestBody PatientRecord patientRecord) {
        List<PatientRecord> patientRecordList=new ArrayList<>();
        patientRecordList.add(patientRecordService.updatePatientRecord(id, patientRecord));
        return new PatientRecordResponse("ok",patientRecordList);
    }

    @DeleteMapping("/del/{id}")
    public void deletePatientRecord(@PathVariable UUID id) {
        patientRecordService.deletePatientRecord(id);
    }
}









