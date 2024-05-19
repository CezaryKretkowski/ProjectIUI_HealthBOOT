package com.example.ProjectIUI_HealthBOOT.Services.AudiToTextService;


import com.example.ProjectIUI_HealthBOOT.Dtos.AudiToTextResponse;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface IAudioToTextServices {
    List<AudiToTextResponse> generateTextFromWma(String path) throws RuntimeException;
}
