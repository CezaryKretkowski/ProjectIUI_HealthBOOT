package com.example.ProjectIUI_HealthBOOT.Services.Audio;

import com.example.ProjectIUI_HealthBOOT.Dtos.UploadResponse;
import com.example.ProjectIUI_HealthBOOT.Entity.AudioFile.AudioFile;
import com.example.ProjectIUI_HealthBOOT.Entity.AudioFile.AudioFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Service
public class AudioFileService {

    private String path= "src/main/resources/Records/";
    private final AudioFileRepository audioFileRepository;

    @Autowired
    public AudioFileService(AudioFileRepository audioFileRepository) {
        this.audioFileRepository = audioFileRepository;
    }

    public List<AudioFile> getAllAudioFiles() {
        return audioFileRepository.findAll();
    }

    public AudioFile getAudioFileById(UUID id) {
        return audioFileRepository.findById(id).orElse(null);
    }

    public UploadResponse addAudioFile(MultipartFile audioFile) {
        AudioFile a=new AudioFile();
        a.setUuid(UUID.randomUUID());
        if (audioFile.isEmpty()) {
            return new UploadResponse(UUID.randomUUID(),"Plik nie został przesłąny",HttpStatus.BAD_REQUEST.toString());
        }

        try {
            // Tworzenie ścieżki docelowej
            Path uploadPath = Paths.get(path);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Zapis przesłanego pliku
            String fileName=new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
            a.setFileName(fileName);
            Path filePath = uploadPath.resolve(fileName+".wav");//todo format audio powinien nadawać się automatycznie
            Files.copy(audioFile.getInputStream(), filePath);//todo jak będzie id lekarza to tutaj dodać

            audioFileRepository.save(a);


            return new UploadResponse(a.getUuid(),filePath.toString(),HttpStatus.OK.toString());
        } catch (IOException e) {
            return new UploadResponse(UUID.randomUUID(),"Wystąpił błąd podczas zapisu pliku.",HttpStatus.INTERNAL_SERVER_ERROR.toString());
        }
    }

    public AudioFile updateAudioFile(UUID id, AudioFile updatedAudioFile) {
        return audioFileRepository.findById(id).map(audioFile -> {
            audioFile.setFileName(updatedAudioFile.getFileName());
            return audioFileRepository.save(audioFile);
        }).orElse(null);
    }

    public void deleteAudioFile(UUID id) {
        audioFileRepository.deleteById(id);
    }
}
