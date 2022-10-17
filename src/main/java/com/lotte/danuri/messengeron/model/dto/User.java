package com.lotte.danuri.messengeron.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.util.ArrayList;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class User {

    @Field("_id")
    @Id
    private String userId;

    private String userName;

    private ArrayList<String> fcmToken;

    private ArrayList<RoomData> roomList;


}
