package com.example.ProjectIUI_HealthBOOT.Controllers;

import com.example.ProjectIUI_HealthBOOT.Dtos.AudiToTextResponse;
import com.example.ProjectIUI_HealthBOOT.Dtos.UploadResponse;
import com.example.ProjectIUI_HealthBOOT.Services.AudiToTextService.IAudioToTextServices;
import com.example.ProjectIUI_HealthBOOT.Services.UploadService.IUploadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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

    public UploadController(IUploadService uploadService, IAudioToTextServices audioToTextServices) {
        this.uploadService = uploadService;
        this.audioToTextServices = audioToTextServices;
    }

    @PostMapping("/audio")
    public List<AudiToTextResponse> uploadFile(@RequestParam("file") MultipartFile file) {
        var response = uploadService.saveFile(file, UUID.randomUUID().toString());
        return audioToTextServices.generateTextFromWma(response.text());
    }
}
