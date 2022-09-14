package com.lotte.danuri.messengeron.dto;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
public class Room {

    @Id
    private String userId;

    private ArrayList<RoomData> roomList;
    private LocalDateTime lastActivity;

}
