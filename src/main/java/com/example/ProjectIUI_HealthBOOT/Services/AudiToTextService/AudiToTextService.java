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
import java.util.concurrent.Semaphore;

@Service
public class AudiToTextService implements IAudioToTextServices {
    @Value( "${Speech.Key}" )
    private String speechKey;
    @Value( "${Speech.Region}" )
    private String speechRegion;
    @Value( "${Speech.Language}" )
    private String speechLanguage;
    private static Semaphore stopTranslationWithFileSemaphore;

    private final  ITextRecognitionService recognitionService;

    public AudiToTextService(ITextRecognitionService recognitionService) {
        this.recognitionService = recognitionService;
    }

    private List<AudiToTextResponse> recognizeGetText(SpeechConfig speechConfig, String path) throws ExecutionException, InterruptedException {
        String result ="";

        AudioConfig audioConfig = AudioConfig.fromWavFileInput(path);
        SpeechRecognizer speechRecognizer = new SpeechRecognizer(speechConfig, audioConfig);
        StringBuilder builder = new StringBuilder();
        stopTranslationWithFileSemaphore = new Semaphore(0);

        speechRecognizer.recognizing.addEventListener((s, e) -> {
            System.out.println("RECOGNIZING: Text=" + e.getResult().getText());
        });

        speechRecognizer.recognized.addEventListener((s, e) -> {
            if (e.getResult().getReason() == ResultReason.RecognizedSpeech) {
                System.out.println("RECOGNIZED: Text=" + e.getResult().getText());
                builder.append(e.getResult().getText());
            }
            else if (e.getResult().getReason() == ResultReason.NoMatch) {
                System.out.println("NOMATCH: Speech could not be recognized.");
            }
        });

        speechRecognizer.canceled.addEventListener((s, e) -> {
            System.out.println("CANCELED: Reason=" + e.getReason());

            if (e.getReason() == CancellationReason.Error) {
                System.out.println("CANCELED: ErrorCode=" + e.getErrorCode());
                System.out.println("CANCELED: ErrorDetails=" + e.getErrorDetails());
                System.out.println("CANCELED: Did you set the speech resource key and region values?");
            }

            stopTranslationWithFileSemaphore.release();
        });

        speechRecognizer.sessionStopped.addEventListener((s, e) -> {
            System.out.println("\n    Session stopped event.");
            stopTranslationWithFileSemaphore.release();
        });

        speechRecognizer.startContinuousRecognitionAsync().get();


        stopTranslationWithFileSemaphore.acquire();

        speechRecognizer.stopContinuousRecognitionAsync().get();

        if (!builder.isEmpty()) {
            result = builder.toString();

            var list = recognitionService.GetDataFromTranscription(result);
            if(list.isEmpty()) {
                var first = new AudiToTextResponse(null, result, "Failed to get data", "", "", "");
                list = new ArrayList<AudiToTextResponse>();
                list.add(first);
            }
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
