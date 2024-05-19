package com.example.ProjectIUI_HealthBOOT.Services.AudiToTextService;

import com.example.ProjectIUI_HealthBOOT.Dtos.AudiToTextResponse;
import com.microsoft.cognitiveservices.speech.*;
import com.microsoft.cognitiveservices.speech.audio.AudioConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
public class AudiToTextService implements IAudioToTextServices {
    @Value( "${Speech.Key}" )
    private String speechKey;
    @Value( "${Speech.Region}" )
    private String speechRegion;
    @Value( "${Speech.Language}" )
    private String speechLanguage;

    private List<AudiToTextResponse> recognizeGetText(SpeechConfig speechConfig, String path) throws ExecutionException, InterruptedException {
        String result ="";

        AudioConfig audioConfig = AudioConfig.fromWavFileInput(path);
        SpeechRecognizer speechRecognizer = new SpeechRecognizer(speechConfig, audioConfig);
        Future<SpeechRecognitionResult> task = speechRecognizer.recognizeOnceAsync();
        SpeechRecognitionResult speechRecognitionResult = task.get();

        if (speechRecognitionResult.getReason() == ResultReason.RecognizedSpeech) {
            result = speechRecognitionResult.getText();
            var first = new AudiToTextResponse(UUID.randomUUID(),result,"Ok","00022308912","Iwona","Jakas");
            var list = new ArrayList<AudiToTextResponse>();
            list.add(first);
            return list;
        }
        else  {
            result = "Failed to convert audio to text";
            throw new RuntimeException(result);
        }

    }

    @Override
    public List<AudiToTextResponse> generateTextFromWma(String path) throws RuntimeException{
        try {
            System.out.println(speechKey + " " + speechRegion);
            SpeechConfig speechConfig = SpeechConfig.fromSubscription(speechKey, speechRegion);
            speechConfig.setSpeechRecognitionLanguage(speechLanguage);
            return recognizeGetText(speechConfig,path);

        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
