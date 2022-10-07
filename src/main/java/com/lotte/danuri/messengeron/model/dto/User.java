package com.lotte.danuri.messengeron.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.util.ArrayList;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Field("_id")
    @Id
    private String userId;

    private String fcmToken;

    private ArrayList<RoomData> roomList;


}
