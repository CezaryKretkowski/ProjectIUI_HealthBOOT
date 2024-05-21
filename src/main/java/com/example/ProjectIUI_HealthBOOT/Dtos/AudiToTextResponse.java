package com.example.ProjectIUI_HealthBOOT.Dtos;

import java.util.UUID;

public record AudiToTextResponse(UUID id, String text,String status,String pesel,String name ,String lastName) {
}
