package com.lotte.danuri.messengeron.controller;

import com.lotte.danuri.messengeron.model.dto.Message;
import com.lotte.danuri.messengeron.model.vo.MessagesVo;
import com.lotte.danuri.messengeron.service.ChatService;
import com.lotte.danuri.messengeron.service.RoomService;
import com.lotte.danuri.messengeron.model.vo.MessageVo;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("chat")
public class ChatController {

    @Autowired
    RoomService roomService;
    @Autowired
    ChatService chatService;


    @PostMapping("message")
    @ApiOperation(value = "sendMessage")
    ResponseEntity pushMessage(@RequestBody MessageVo vo) {
        chatService.pushMessage(vo.getRoomId(), vo.getMessage());
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("messages")
    @ApiOperation(value = "sendMessages")
    ResponseEntity pushMessages(@RequestBody MessagesVo vo) {
        chatService.pushMessages(vo.getRoomIds(), vo.getMessage());
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("messages/{roomId}")
    @ApiOperation(value = "getMessages")
    ResponseEntity<List<Message>> getMessages(@PathVariable String roomId) {

        return new ResponseEntity<>(chatService.getMessages(new ObjectId(roomId)), HttpStatus.OK);
    }

    @GetMapping("newMessages/{userId}/{roomId}/{createdAt}")
    @ApiOperation(value = "getNewMessages")
    ResponseEntity<List<Message>> getNewMessages(@PathVariable String userId,@PathVariable String roomId, @PathVariable String createdAt) {
        return new ResponseEntity<>(chatService.getNewMessages(userId,new ObjectId(roomId), LocalDateTime.parse(createdAt)), HttpStatus.OK);
    }
}
