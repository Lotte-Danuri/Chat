package com.lotte.danuri.messengeron.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;
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
public class Room {

    @Field("_id")
    @Id
    private String userId;

    private ArrayList<RoomData> roomList;


}
