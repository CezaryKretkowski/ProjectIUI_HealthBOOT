package com.example.ProjectIUI_HealthBOOT.Controllers;

import com.example.ProjectIUI_HealthBOOT.Dtos.AudiToTextResponse;
import com.example.ProjectIUI_HealthBOOT.Services.AudiToTextService.IAudioToTextServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    private final IAudioToTextServices audioToTextServices;

    public TestController(IAudioToTextServices audioToTextServices){
        this.audioToTextServices = audioToTextServices;
    }
    @RequestMapping("/GetText")
    public AudiToTextResponse GenerateText(){
        return audioToTextServices.generateTextFromWma("src/main/resources/Records/Test/TestRecord.wav");
    }
}
