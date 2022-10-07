package com.lotte.danuri.messengeron;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.util.TimeZone;

@SpringBootApplication
public class MessengeronApplication {



	public static void main(String[] args) {
		SpringApplication.run(MessengeronApplication.class, args);
	}



}
