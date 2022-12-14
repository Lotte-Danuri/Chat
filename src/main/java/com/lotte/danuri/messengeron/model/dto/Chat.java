package com.lotte.danuri.messengeron.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Chat {


    @Field("_id")
    @Id
    @JsonSerialize(using= ToStringSerializer.class)
    private ObjectId chatId;

    private String contentType;

    private String content;

    private String sendBy;


    private String source;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createdAt;
}
