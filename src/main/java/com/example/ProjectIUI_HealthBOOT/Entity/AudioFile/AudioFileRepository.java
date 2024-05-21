package com.example.ProjectIUI_HealthBOOT.Entity.AudioFile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AudioFileRepository extends JpaRepository<AudioFile, UUID> {
}