package com.example.ProjectIUI_HealthBOOT.Services.AudiToTextService;


import com.example.ProjectIUI_HealthBOOT.Dtos.AudiToTextResponse;

public interface IAudioToTextServices {
    AudiToTextResponse GenerateTextFromMp3(String path);
}
