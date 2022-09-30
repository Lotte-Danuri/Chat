package com.lotte.danuri.messengeron.model.vo;


import com.lotte.danuri.messengeron.model.dto.Chat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class RoomListVo {

    @Id
    private Chat chat;

    private String receiverId;

    private int countNewMessages;

}
