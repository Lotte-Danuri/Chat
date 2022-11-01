package com.lotte.danuri.messengeron.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.FileInputStream;

@Configuration
public class FcmServiceInitializer {

    @Bean
    public void run() throws Exception {
        try {
            FirebaseApp.getInstance();
        } catch (Exception e) {
            try {
                FirebaseOptions options = FirebaseOptions.builder()
                        .setProjectId("luxon-c4fb2")
                        .setCredentials(GoogleCredentials.fromStream(new ClassPathResource("google-service.json").getInputStream()))
                        .build();

                FirebaseApp.initializeApp(options);
            } catch (Exception e1) {
            }
        }
    }
}