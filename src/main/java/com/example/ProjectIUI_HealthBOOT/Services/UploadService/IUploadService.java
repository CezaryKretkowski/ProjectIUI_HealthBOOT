package com.example.ProjectIUI_HealthBOOT.Services.UploadService;

import com.example.ProjectIUI_HealthBOOT.Dtos.UploadResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
public interface IUploadService {
    UploadResponse saveFile(MultipartFile audio, String IDLEKARZA);
}
