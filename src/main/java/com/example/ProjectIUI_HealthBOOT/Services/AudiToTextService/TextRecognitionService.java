package com.example.ProjectIUI_HealthBOOT.Services.AudiToTextService;

import com.example.ProjectIUI_HealthBOOT.Dtos.AudiToTextResponse;
import com.example.ProjectIUI_HealthBOOT.Dtos.TextRecognitionResponse;
import com.example.ProjectIUI_HealthBOOT.Entity.Patient.Patient;
import com.example.ProjectIUI_HealthBOOT.Services.Patient.PatientService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
public class TextRecognitionService implements ITextRecognitionService{

    private final HttpClient httpClient;
    private final Gson jsonHelper;

    @Value( "${TextRecognition.url}" )
    private String textRecognitionUrl;

    @Autowired
    private PatientService patientService;
    public TextRecognitionService(HttpClient httpClient, Gson jsonHelper) {
        this.httpClient = httpClient;
        this.jsonHelper = jsonHelper;
    }

    public List<TextRecognitionResponse>  GetResponse(String text)throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(textRecognitionUrl))
                .POST(HttpRequest.BodyPublishers.ofString("{ \"text\": \""+text.toLowerCase()+"\"}"))
                .setHeader("Content-Type","application/json")
                .build();
        HttpResponse<String> response =httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if(response.statusCode()==200){
            List<TextRecognitionResponse> list = jsonHelper.fromJson(response.body(), new TypeToken<List<TextRecognitionResponse>>() {}.getType());
            return list;
        }else{
            throw new RuntimeException("Failed to recognize text");
        }
    }

    @Override
    public List<AudiToTextResponse> GetDataFromTranscription(String transcriptionText) {
        try{
            var list = new ArrayList<AudiToTextResponse>();
            for(TextRecognitionResponse i : GetResponse(transcriptionText)){
                var patient = patientService.getAllPatients().stream().filter(x->x.getPesel().equals(i.id)).findFirst();
                UUID id = null;
                if(patient.isPresent()){
                    id = patient.get().getUuid();
                }
                list.add(new AudiToTextResponse(id,i.text,"ok",i.id,i.name,i.lastName));
            }
            return list;
        }catch (Exception e){
            var list = new ArrayList<AudiToTextResponse>();
            list.add(new AudiToTextResponse(null,transcriptionText,"failed","","",""));
            return list;
        }


    }
}
