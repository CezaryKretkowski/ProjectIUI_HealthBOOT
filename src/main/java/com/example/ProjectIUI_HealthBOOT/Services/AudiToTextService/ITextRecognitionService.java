package com.example.ProjectIUI_HealthBOOT.Services.AudiToTextService;

import com.example.ProjectIUI_HealthBOOT.Dtos.AudiToTextResponse;

import java.util.List;

public interface ITextRecognitionService {

    List<AudiToTextResponse> GetDataFromTranscription(String transcriptionText);
}
