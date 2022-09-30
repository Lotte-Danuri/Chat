package com.lotte.danuri.messengeron.model.vo;

import com.lotte.danuri.messengeron.model.dto.Message;
import com.lotte.danuri.messengeron.service.ChatService;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ImageVo {

    @NonNull
    private String contentType;
    @NonNull
    MultipartFile multipartFile;
    @NonNull
    private String sendBy;
    private String source;
    @NonNull
    private String id;


    @Autowired
    ChatService chatService;

    public Message getImageVo() {
        return new Message(new ObjectId(), this.getContentType(), chatService.pushImage(this.multipartFile), this.getSendBy(), this.getSource(), LocalDateTime.now());
    }

    public ObjectId getRoomId() {
        return new ObjectId(this.getId());
    }
}
