package com.example.ProjectIUI_HealthBOOT.Entity.Patient;

import com.example.ProjectIUI_HealthBOOT.Dtos.PatientCreateRequest;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class Patient {
    public Patient(){}
    public Patient(PatientCreateRequest request) {
        this.firstName = request.firstName();
        this.lastName = request.lastName();
        this.pesel = request.pesel();
    }

    public Patient(String firstName, String lastName, String pesel) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
    }

    @Id
    @GeneratedValue
    private UUID uuid;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String pesel;

    // Getters and Setters
    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }
}
