package com.lotte.danuri.messengeron.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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
public class Message {


    @Field("_id")
    @Id
    @JsonSerialize(using= ToStringSerializer.class)
    private ObjectId messageId;

    private String contentType;

    private String content;

    private String sendBy;

    private String source;
}
