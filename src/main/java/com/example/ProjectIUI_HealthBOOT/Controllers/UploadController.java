package com.example.ProjectIUI_HealthBOOT.Controllers;
import com.example.ProjectIUI_HealthBOOT.Dtos.AudiToTextResponse;
import com.example.ProjectIUI_HealthBOOT.Dtos.UploadResponse;
import com.example.ProjectIUI_HealthBOOT.Entity.AudioFile.AudioFile;
import com.example.ProjectIUI_HealthBOOT.Services.AudiToTextService.IAudioToTextServices;
import com.example.ProjectIUI_HealthBOOT.Services.Audio.AudioFileService;

import com.example.ProjectIUI_HealthBOOT.Services.UploadService.IUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/upload")
public class UploadController {
    private final IUploadService uploadService;

    private final IAudioToTextServices audioToTextServices;

    public UploadController(IUploadService uploadService, IAudioToTextServices audioToTextServices, AudioFileService audioFileService) {
        this.uploadService = uploadService;
        this.audioToTextServices = audioToTextServices;
        this.audioFileService = audioFileService;
    }

    //@PostMapping("/audio")
    public List<AudiToTextResponse> uploadFile(@RequestParam("file") MultipartFile file) {
        var response = uploadService.saveFile(file, UUID.randomUUID().toString());
        return audioToTextServices.generateTextFromWma(response.text());
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

    @PostMapping("/audio")
    public List<AudiToTextResponse> addAudioFile(@RequestParam("file") MultipartFile file) {
        var response = audioFileService.addAudioFile(file);
        return audioToTextServices.generateTextFromWma(response.text());
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
