package com.lotte.danuri.messengeron.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;

@Configuration
public class FcmServiceInitializer {

    @Bean
    public void run() throws Exception {
        try {
            FirebaseApp.getInstance();
        } catch (Exception e) {
            try {
                String path = System.getProperty("user.home");
                System.out.println(path+"/key/luxon-c4fb2-firebase-adminsdk-fl48h-e2292ae972.json");
                FirebaseOptions options = FirebaseOptions.builder()
                        .setProjectId("luxon-c4fb2")
                        .setCredentials(GoogleCredentials.fromStream(new FileInputStream(path+"/key/luxon-c4fb2-firebase-adminsdk-fl48h-e2292ae972.json")))
                        .build();

                FirebaseApp.initializeApp(options);
            } catch (Exception e1) {
            }
        }
    }
}