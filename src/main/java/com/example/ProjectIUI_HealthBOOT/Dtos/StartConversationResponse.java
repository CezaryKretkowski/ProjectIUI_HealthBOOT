package com.example.ProjectIUI_HealthBOOT.Dtos;

import java.util.UUID;

public class StartConversationResponse{
    public String conversationId;
    public String token;
    public int expires_in;
    public String streamUrl;
    public UUID referenceGrammarId;
}
