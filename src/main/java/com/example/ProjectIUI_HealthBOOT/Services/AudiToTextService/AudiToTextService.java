package com.example.ProjectIUI_HealthBOOT.Services.AudiToTextService;

import com.example.ProjectIUI_HealthBOOT.Dtos.AudiToTextResponse;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AudiToTextService implements IAudioToTextServices {
    @Override
    public AudiToTextResponse GenerateTextFromMp3(String path) {
        return new AudiToTextResponse(UUID.randomUUID(),"work");
    }
}
