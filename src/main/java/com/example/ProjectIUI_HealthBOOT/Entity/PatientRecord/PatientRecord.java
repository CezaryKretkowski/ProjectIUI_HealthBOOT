package com.example.ProjectIUI_HealthBOOT.Entity.PatientRecord;

import com.example.ProjectIUI_HealthBOOT.Entity.Patient.Patient;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class PatientRecord {

    @Id
    @GeneratedValue
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "patient_uuid", nullable = false)
    private Patient patient;

    @Column(nullable = false)
    private String patientStatus;

    // Getters and Setters
    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getPatientStatus() {
        return patientStatus;
    }

    public void setPatientStatus(String patientStatus) {
        this.patientStatus = patientStatus;
    }
}
