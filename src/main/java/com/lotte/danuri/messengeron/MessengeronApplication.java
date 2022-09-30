package com.lotte.danuri.messengeron;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class MessengeronApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessengeronApplication.class, args);
	}

}
