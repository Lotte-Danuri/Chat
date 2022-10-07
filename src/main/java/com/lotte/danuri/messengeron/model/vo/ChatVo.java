package com.lotte.danuri.messengeron.model.vo;

import com.lotte.danuri.messengeron.model.dto.Chat;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class ChatVo {

    @NonNull
    private String contentType;
    @NonNull
    private String content;
    @NonNull
    private String sendBy;

    private String sendTo;

    private String source;
    @NonNull
    private String id;



    public Chat getChat() {
        return new Chat(new ObjectId(), this.getContentType(), this.getContent(), this.getSendBy(), this.getSource(), LocalDateTime.now());
    }

    public ObjectId getChatRoomId() {
        return new ObjectId(this.getId());
    }

}
