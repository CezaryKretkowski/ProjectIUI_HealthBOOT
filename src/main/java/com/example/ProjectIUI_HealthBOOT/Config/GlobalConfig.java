package com.example.ProjectIUI_HealthBOOT.Config;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.net.http.HttpClient;

@Configuration
public class GlobalConfig {

    @Bean
    public HttpClient httpClient(){
        return HttpClient.newHttpClient();
    }

    @Bean
    public Gson jsonHelper(){
        return new Gson();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000")//ustawienie skąd może przychodzić połączenie
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH")//jakie operacje może wykonywać
                        .allowedHeaders("Content-Type", "Authorization", "X-Requested-With")
                        .allowCredentials(true);
            }
        };
    }
}
