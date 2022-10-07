package com.lotte.danuri.messengeron.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;


@Configuration
public class FcmServiceInitializer {

    @Bean
    public void run() throws Exception {
        try {
            FirebaseApp.getInstance();
        } catch (Exception e) {
            try {
                String path = System.getProperty("user.dir");

                FileInputStream serviceAccount = new FileInputStream(path
                        + "/src/main/resources/firebase/luxon-c4fb2-firebase-adminsdk-fl48h-e2292ae972.json");

                FirebaseOptions options = new FirebaseOptions.Builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();

                FirebaseApp.initializeApp(options);
            } catch (Exception e1) {
            }
        }
    }
}