package com.lotte.danuri.messengeron.model.vo;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.lotte.danuri.messengeron.model.dto.ChatRoom;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class RoomListVo {
    public RoomListVo(ChatRoom chatRoom, String receiverId,String userName, int countNewChats) {
        this.chatRoomId = chatRoom.getChatRoomId();
        if (chatRoom.getChatList().isEmpty()) {
            this.lastChatCreatedAt = null;
            this.lastChatContent = null;
        } else {
            this.lastChatContent = chatRoom.getChatList().get(0).getContent();
            this.lastChatCreatedAt = chatRoom.getChatList().get(0).getCreatedAt();
        }
        this.userName = userName;
        this.valid = chatRoom.isValid();
        this.roomType = chatRoom.getRoomType();
        this.updateAt = chatRoom.getUpdateAt();
        this.receiverId = receiverId;
        this.countNewChats = countNewChats;
    }

    @JsonSerialize(using = ToStringSerializer.class)
    @Id
    private ObjectId chatRoomId;

    private String userName;
    private String lastChatContent;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime lastChatCreatedAt;
    private boolean valid;

    private String roomType;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime updateAt;

    private String receiverId;

    private int countNewChats;

}
