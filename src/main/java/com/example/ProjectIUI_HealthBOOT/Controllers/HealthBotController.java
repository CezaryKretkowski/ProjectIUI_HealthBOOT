package com.example.ProjectIUI_HealthBOOT.Controllers;

import com.example.ProjectIUI_HealthBOOT.Dtos.HealthBotRequest;
import com.example.ProjectIUI_HealthBOOT.Dtos.HealthBotResponse;
import com.example.ProjectIUI_HealthBOOT.Services.HealthBotService.IHealthBotService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/HealthBot")
public class HealthBotController {

    private final IHealthBotService healthBotService;

    public HealthBotController(IHealthBotService healthBotService) {
        this.healthBotService = healthBotService;
    }

    @PostMapping("/askQuestion")
    public HealthBotResponse AskQuestion(@RequestBody HealthBotRequest request){
        return healthBotService.AskQuestion(request.question());
    }
}
