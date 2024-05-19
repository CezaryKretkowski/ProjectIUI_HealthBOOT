package com.example.ProjectIUI_HealthBOOT.Controllers;

import com.example.ProjectIUI_HealthBOOT.Dtos.UploadResponse;
import com.example.ProjectIUI_HealthBOOT.Entity.AudioFile.AudioFile;
import com.example.ProjectIUI_HealthBOOT.Services.Audio.AudioFileService;
import com.example.ProjectIUI_HealthBOOT.Services.UploadService.IUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/upload")
public class UploadController {
    private final IUploadService uploadService;
    @Autowired
    public UploadController(IUploadService uploadService, AudioFileService audioFileService) {
        this.uploadService = uploadService;
        this.audioFileService = audioFileService;
    }

    @PostMapping("/audio")
    public UploadResponse uploadFile(@RequestParam("file") MultipartFile file) {
        return uploadService.saveFile(file,null);
    }

    private final AudioFileService audioFileService;



    @GetMapping
    public List<AudioFile> getAllAudioFiles() {
        return audioFileService.getAllAudioFiles();
    }

    @GetMapping("/{id}")
    public AudioFile getAudioFileById(@PathVariable UUID id) {
        return audioFileService.getAudioFileById(id);
    }

    @PostMapping
    public AudioFile addAudioFile(@RequestBody AudioFile audioFile) {
        return audioFileService.addAudioFile(audioFile);
    }

    @PutMapping("/{id}")
    public AudioFile updateAudioFile(@PathVariable UUID id, @RequestBody AudioFile audioFile) {
        return audioFileService.updateAudioFile(id, audioFile);
    }

    @DeleteMapping("/{id}")
    public void deleteAudioFile(@PathVariable UUID id) {
        audioFileService.deleteAudioFile(id);
    }
}
