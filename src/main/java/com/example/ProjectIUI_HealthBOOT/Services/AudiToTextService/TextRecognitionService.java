package com.example.ProjectIUI_HealthBOOT.Services.AudiToTextService;

import com.example.ProjectIUI_HealthBOOT.Dtos.AudiToTextResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
public class TextRecognitionService implements ITextRecognitionService{

    private AudiToTextResponse GetResponse(String fragment){
        try {
            int i = fragment.indexOf("pesel");
            var nameLastName = fragment.substring(0, i).split(" ");
            int s = fragment.indexOf("stan");
            var pesel = fragment.substring(i, s);
            var stan = fragment.substring(s);
            return new AudiToTextResponse(UUID.randomUUID(), stan, "ok", pesel, nameLastName[0], nameLastName[1]);
        }catch (Exception e){
            return new AudiToTextResponse(UUID.randomUUID(), fragment, "cannot recognize","","","");
        }
    }
    @Override
    public List<AudiToTextResponse> GetDataFromTranscription(String transcriptionText) {
        var results = transcriptionText.toLowerCase().split("pacjęt");
        var responseData = new ArrayList<AudiToTextResponse>();
        for(String i : results){
            responseData.add(GetResponse(i));
        }
        return responseData;
    }
}
