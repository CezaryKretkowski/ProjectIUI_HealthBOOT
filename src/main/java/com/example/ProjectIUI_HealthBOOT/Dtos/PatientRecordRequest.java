package com.example.ProjectIUI_HealthBOOT.Dtos;

import java.util.UUID;

public class PatientRecordRequest {
    private UUID patientId;
    private String state;

    public UUID getPatientId() {
        return patientId;
    }

    public void setPatientId(UUID patientId) {
        this.patientId = patientId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
