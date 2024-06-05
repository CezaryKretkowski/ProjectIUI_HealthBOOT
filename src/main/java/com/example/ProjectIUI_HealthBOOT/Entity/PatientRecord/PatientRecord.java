package com.example.ProjectIUI_HealthBOOT.Entity.PatientRecord;

import com.example.ProjectIUI_HealthBOOT.Entity.AudioFile.AudioFile;
import com.example.ProjectIUI_HealthBOOT.Entity.Patient.Patient;
import jakarta.persistence.*;
import jdk.jfr.Timestamp;

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

    @ManyToOne
    @JoinColumn(name="audio_uuid")
    private AudioFile audioFile;

    @Column(nullable = false)
    private String roundDate;

    @Column(nullable = false)
    private String roundTime;

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

    public AudioFile getAudioFile() {
        return audioFile;
    }

    public void setAudioFile(AudioFile audioFile) {
        this.audioFile = audioFile;
    }

    public String getRoundDate() {
        return roundDate;
    }

    public void setRoundDate(String roundDate) {
        this.roundDate = roundDate;
    }

    public String getRoundTime() {
        return roundTime;
    }

    public void setRoundTime(String roundTime) {
        this.roundTime = roundTime;
    }
}
