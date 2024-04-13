package com.example.ProjectIUI_HealthBOOT.Services.UploadService;

import com.example.ProjectIUI_HealthBOOT.DBHelper.DBHelper;
import com.example.ProjectIUI_HealthBOOT.Dtos.UploadResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

@Service
public class UploadService implements IUploadService {
    private String path= "src/main/resources/Records/";
    private DBHelper helper;

    public UploadService(DBHelper helper) {
        this.helper = helper;
    }

    @Override
    public UploadResponse saveFile(MultipartFile audio, String IDLEKARZA) {
        IDLEKARZA="ADMIN";//todo IDLEKARZA będzie przekazywane z frontendu
        if (audio.isEmpty()) {
            return new UploadResponse(UUID.randomUUID(),"Plik nie został przesłąny",HttpStatus.BAD_REQUEST.toString());
        }

        try {
            // Tworzenie ścieżki docelowej
            Path uploadPath = Paths.get(path+IDLEKARZA);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Zapis przesłanego pliku
            String fileName=new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
            Path filePath = uploadPath.resolve(fileName+".wav");//todo format audio powinien nadawać się automatycznie
            Files.copy(audio.getInputStream(), filePath);

            helper.initializeTable();

            helper.insertData(IDLEKARZA.toString(),fileName);

            return new UploadResponse(UUID.randomUUID(),"Plik został pomyślnie przesłany i zapisany.",HttpStatus.OK.toString());
        } catch (IOException e) {
            return new UploadResponse(UUID.randomUUID(),"Wystąpił błąd podczas zapisu pliku.",HttpStatus.INTERNAL_SERVER_ERROR.toString());
        }
    }
}
