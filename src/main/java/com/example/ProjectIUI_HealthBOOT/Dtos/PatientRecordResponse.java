package com.example.ProjectIUI_HealthBOOT.Dtos;

import com.example.ProjectIUI_HealthBOOT.Entity.PatientRecord.PatientRecord;

import java.util.List;

public record PatientRecordResponse(String status, List<PatientRecord> RecordList) {
}
