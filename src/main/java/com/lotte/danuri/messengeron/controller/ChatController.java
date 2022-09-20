package com.lotte.danuri.messengeron.controller;

import com.lotte.danuri.messengeron.dto.Chat;
import com.lotte.danuri.messengeron.dto.Message;
import com.lotte.danuri.messengeron.service.ChatService;
import com.lotte.danuri.messengeron.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("chat")
public class ChatController {

    @Autowired
    RoomService roomService;
    @Autowired
    ChatService chatService;

    @PostMapping("chat")
    void createChatRoom(String userId, String receiverId) {
        //빈방 생성
        ObjectId roomId = chatService.createChat();

        //유저에게 주입
        roomService.createChatRoom(userId, receiverId);
    }

    @PostMapping("message")
    void pushMessage(Chat chat, Message message) {
        chatService.pushMessage(chat, message);
    }

}
