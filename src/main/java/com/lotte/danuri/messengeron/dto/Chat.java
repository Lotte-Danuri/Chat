package com.lotte.danuri.messengeron.dto;


import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
public class Chat {

    @Id
    private ObjectId id;

    private ArrayList<Message> messageList;
    private LocalDateTime createdAt;

}
