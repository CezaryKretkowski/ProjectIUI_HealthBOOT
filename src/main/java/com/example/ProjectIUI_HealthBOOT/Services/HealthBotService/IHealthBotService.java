package com.example.ProjectIUI_HealthBOOT.Services.HealthBotService;

import com.example.ProjectIUI_HealthBOOT.Dtos.HealthBotResponse;

public interface IHealthBotService {
    HealthBotResponse AskQuestion(String question);
}
