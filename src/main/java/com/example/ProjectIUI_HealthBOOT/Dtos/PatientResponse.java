package com.example.ProjectIUI_HealthBOOT.Dtos;

import com.example.ProjectIUI_HealthBOOT.Entity.Patient.Patient;

import java.util.List;

public record PatientResponse(String Status, List<Patient> patientList) {
}
