package com.example.ProjectIUI_HealthBOOT.Services.HealthBotService;

import com.example.ProjectIUI_HealthBOOT.Dtos.HealthBotQuestionRequest;
import com.example.ProjectIUI_HealthBOOT.Dtos.HealthBotResponse;
import com.example.ProjectIUI_HealthBOOT.Dtos.StartConversationResponse;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;


@Service
public class HealthBotService implements IHealthBotService{
    @Value( "${HealthBot.Secret}" )
    private String healthBotSecret;
    @Value( "${HealthBot.StartConversationUrl}" )
    private String startConversationUrl;
    private String conversationId;
    private String token;
    private String questionId;
    private final HttpClient httpClient;
    private final Gson jsonHelper;


    public HealthBotService(HttpClient httpClient,Gson jsonHelper) {
        this.httpClient = httpClient;
        this.jsonHelper = jsonHelper;
    }

    private void StartConversation() throws URISyntaxException, IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI(startConversationUrl))
                .POST(HttpRequest.BodyPublishers.noBody())
                .setHeader("Authorization","Bearer "+healthBotSecret)
                .build();

        HttpResponse<String> response =httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        StartConversationResponse startConversationResponse = jsonHelper.fromJson(response.body(), StartConversationResponse.class);
        conversationId = startConversationResponse.conversationId;
        token = startConversationResponse.token;
    }

    private void SendQuestion(String question) throws URISyntaxException, IOException, InterruptedException {

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI(startConversationUrl+"/"+conversationId+"/activities"))
                .POST(HttpRequest.BodyPublishers.ofString(new HealthBotQuestionRequest("en-EN",question).toString()))
                .setHeader("Authorization","Bearer "+token)
                .setHeader("Content-Type","application/json")
                .build();
        HttpResponse<String> response =httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        if(response.statusCode()==403)
        {
            token = null;
            conversationId = null;
            return;
        }
        var responseMap = jsonHelper.fromJson(response.body(),Map.class);
        questionId = responseMap.get("id").toString();
        questionId = questionId.substring(questionId.length()-7);
        System.out.println(questionId);
    }

    private String GetAnswer() throws URISyntaxException, IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI(startConversationUrl+"/"+conversationId+"/activities?watermark="+questionId))
                .GET()
                .setHeader("Authorization","Bearer "+token)
                .setHeader("Content-Type","application/json")
                .build();
        HttpResponse<String> response =httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        var responseMap = jsonHelper.fromJson(response.body(),Map.class);
        String jsonTab =responseMap.get("activities").toString();
        var nr =jsonTab.indexOf("speak=Developer notice:");
        if(nr!=-1){
            jsonTab=jsonTab.substring(nr+"speak=Developer notice:".length());
        }
        var i = jsonTab.indexOf("speak=");
        var message = jsonTab.substring(i);
        i = message.indexOf(", inputHint");

        System.out.println(jsonTab);
        return message.substring("speak=".length(),i);

    }
    @Override
    public HealthBotResponse AskQuestion(String question) throws RuntimeException{

        try {

            if(conversationId==null || token == null){
                StartConversation();
            }
            SendQuestion(question);
            String errorMessage = "Conversation was expired pleas resend question. If error persists contact with administrator.";
            if(conversationId==null || token == null)
                return new HealthBotResponse("Session expired", errorMessage);
            var answer = GetAnswer();
            return new HealthBotResponse("Success",answer);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
