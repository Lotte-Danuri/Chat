package com.lotte.danuri.messengeron.dto;

import lombok.Data;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

@Data
public class RoomData {
    private ObjectId roomId;
    private LocalDateTime lastActivate;
}
