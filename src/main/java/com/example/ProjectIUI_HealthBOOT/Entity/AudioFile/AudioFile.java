package com.example.ProjectIUI_HealthBOOT.Entity.AudioFile;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class AudioFile {

    @Id
    @GeneratedValue
    private UUID uuid;

    @Column(nullable = false, unique = true)
    private String fileName;

    // Getters and Setters
    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
