package com.lotte.danuri.messengeron.dto;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class Message {

    private String contentType;
    private LocalDateTime createdAt;
    private String content;
    private String sendBy;


}
