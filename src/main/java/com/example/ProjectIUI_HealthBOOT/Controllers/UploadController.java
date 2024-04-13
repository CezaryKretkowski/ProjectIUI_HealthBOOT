package com.example.ProjectIUI_HealthBOOT.Controllers;

import com.example.ProjectIUI_HealthBOOT.Dtos.UploadResponse;
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


@RestController
@RequestMapping("/upload")
public class UploadController {
    private final IUploadService uploadService;

    public UploadController(IUploadService uploadService) {
        this.uploadService = uploadService;
    }

    @PostMapping("/audio")
    public UploadResponse uploadFile(@RequestParam("file") MultipartFile file) {
        return uploadService.saveFile(file,null);
    }
}
