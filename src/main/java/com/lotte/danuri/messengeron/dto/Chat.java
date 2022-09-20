package com.lotte.danuri.messengeron.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chat {

    @Field("_id")
    @Id
    private ObjectId chatId;

    private ArrayList<Message> messageList;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime UpdateAt;

    private String lastMessage;

    private String roomType;

}
