package com.example.ProjectIUI_HealthBOOT.Dtos;

public class HealthBotQuestionRequest {
    public HealthBotQuestionRequest(String local,String text){
        this.local = local;
        this.text= text;
    }
    public String local;
    public String text;

    @Override
    public String toString() {
        return "{" + "\"local\":" + "\""+local+"\","+
                "\"type\":\"message\","+
                "\"from\":{\"id\":\"defaultUser\"},"+
                "\"text\":" + "\""+text+"\""+
                "}";
    }
}
