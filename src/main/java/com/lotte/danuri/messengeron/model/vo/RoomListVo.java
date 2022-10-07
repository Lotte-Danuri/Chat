package com.lotte.danuri.messengeron.model.vo;


import com.lotte.danuri.messengeron.model.dto.ChatRoom;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class RoomListVo {

    @Id
    private ChatRoom chatRoom;

    private String receiverId;

    private int countNewChats;

}
