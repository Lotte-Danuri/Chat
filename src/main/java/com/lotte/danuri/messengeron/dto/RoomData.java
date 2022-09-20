package com.lotte.danuri.messengeron.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomData {

    @Id
    private ObjectId roomId;

    private String receiverId;
}
