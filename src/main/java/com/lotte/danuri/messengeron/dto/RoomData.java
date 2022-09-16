package com.lotte.danuri.messengeron.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@Data
@AllArgsConstructor
public class RoomData {

    private ObjectId roomId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date lastActivate;

    private String roomType;

    private String receiverId;
}
