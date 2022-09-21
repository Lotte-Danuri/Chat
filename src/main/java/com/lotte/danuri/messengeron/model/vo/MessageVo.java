package com.lotte.danuri.messengeron.model.vo;

import com.lotte.danuri.messengeron.model.dto.Message;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;

@Getter
@RequiredArgsConstructor
public class MessageVo {

    @NonNull
    private String contentType;
    @NonNull
    private String content;
    @NonNull
    private String sendBy;
    private String source;
    @NonNull
    private String id;


    public Message getMessage() {
        return new Message(new ObjectId(), this.getContentType(), this.getContent(), this.getSendBy(), this.getSource());
    }

    public ObjectId getRoomId() {
        return new ObjectId(this.getId());
    }
}
