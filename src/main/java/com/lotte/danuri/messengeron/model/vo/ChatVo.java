package com.lotte.danuri.messengeron.model.vo;

import com.lotte.danuri.messengeron.model.dto.Chat;
import com.lotte.danuri.messengeron.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class ChatVo {

    @NonNull
    private String contentType;
    @NonNull
    private String content;
    @NonNull
    private String sendBy;
    @NonNull
    private String sendTo;

    private String source;
    private String id;

    private final UserService userService;

    public Chat getChat() {
        return new Chat(new ObjectId(), this.getContentType(), this.getContent(), this.getSendBy(), this.getSource(), LocalDateTime.now());
    }

    public ObjectId getChatRoomId() {
        if(this.id == null){
            return userService.findRoomIdByUserId(this.getSendTo(),this.getSendBy()).getChatRoomId();
        }
        else{
            return new ObjectId(this.id);
        }
    }

}
