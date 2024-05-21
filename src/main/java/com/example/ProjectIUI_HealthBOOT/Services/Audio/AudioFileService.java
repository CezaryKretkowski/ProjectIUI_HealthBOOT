package com.example.ProjectIUI_HealthBOOT.Services.Audio;

import com.example.ProjectIUI_HealthBOOT.Entity.AudioFile.AudioFile;
import com.example.ProjectIUI_HealthBOOT.Entity.AudioFile.AudioFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AudioFileService {

    private final AudioFileRepository audioFileRepository;

    @Autowired
    public AudioFileService(AudioFileRepository audioFileRepository) {
        this.audioFileRepository = audioFileRepository;
    }

    public List<AudioFile> getAllAudioFiles() {
        return audioFileRepository.findAll();
    }

    public AudioFile getAudioFileById(UUID id) {
        return audioFileRepository.findById(id).orElse(null);
    }

    public AudioFile addAudioFile(AudioFile audioFile) {
        return audioFileRepository.save(audioFile);
    }

    public AudioFile updateAudioFile(UUID id, AudioFile updatedAudioFile) {
        return audioFileRepository.findById(id).map(audioFile -> {
            audioFile.setFileName(updatedAudioFile.getFileName());
            return audioFileRepository.save(audioFile);
        }).orElse(null);
    }

    public void deleteAudioFile(UUID id) {
        audioFileRepository.deleteById(id);
    }
}
