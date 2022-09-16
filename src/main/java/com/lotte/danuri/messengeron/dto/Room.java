package com.lotte.danuri.messengeron.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;

@Document
@Data
@AllArgsConstructor
public class Room {

    @Field("_id")
    @Id
    private String userId;

    private ArrayList<RoomData> roomList;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date lastActivity;

}
