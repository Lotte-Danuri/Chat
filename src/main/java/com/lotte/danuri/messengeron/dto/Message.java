package com.lotte.danuri.messengeron.dto;

import com.mongodb.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
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
    private ObjectId messageId;

    private String contentType;

    private String content;

    private String sendBy;

    @Nullable
    private String source;
}
