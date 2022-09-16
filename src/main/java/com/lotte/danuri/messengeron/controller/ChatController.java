package com.lotte.danuri.messengeron.controller;

import com.lotte.danuri.messengeron.dto.Chat;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("chat")
public class ChatController {

    @PostMapping("chat")
    void createChat(Chat chat){

    }
}
