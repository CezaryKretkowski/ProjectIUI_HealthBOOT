package com.example.ProjectIUI_HealthBOOT;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.file.Files;

public class test {
    public static void main(String[] args) {
        String serverUrl = "http://localhost:8080/upload/audio"; // Adres URL serwera Spring Boot

        try {
            String filePath = "D:\\studia\\2Stopień\\to\\TestRecord.wav"; // Ścieżka do pliku audio
            File file = new File(filePath);

            // Utwórz połączenie HTTP
            URL url = new URL(serverUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Ustaw parametry żądania
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            String boundary = Long.toHexString(System.currentTimeMillis()); // Wygeneruj unikalny boundary
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary); // Ustaw boundary dla multipart
            try (OutputStream output = connection.getOutputStream();
                 PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, "UTF-8"), true)) {

                // Zapisz dane pliku
                writer.append("--" + boundary).append("\r\n");
                writer.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + file.getName() + "\"").append("\r\n");
                writer.append("Content-Type: " + HttpURLConnection.guessContentTypeFromName(file.getName())).append("\r\n");
                writer.append("\r\n").flush();
                Files.copy(file.toPath(), output);
                output.flush(); // Wysłanie danych pliku

                // Zakończenie żądania
                writer.append("\r\n").flush();
                writer.append("--" + boundary + "--").append("\r\n").flush();
            }

            // Uzyskaj strumień wyjściowy do przesyłania danych
            OutputStream outputStream = connection.getOutputStream();
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            fileInputStream.close();
            outputStream.flush();

            // Pobierz odpowiedź od serwera
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Odczytaj odpowiedź serwera
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String response = in.readLine();
                System.out.println("Odpowiedź serwera: " + response);
                in.close();
            } else {
                System.out.println("Błąd podczas wysyłania pliku. Kod odpowiedzi: " + responseCode);
            }

            // Zamknij połączenie
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
