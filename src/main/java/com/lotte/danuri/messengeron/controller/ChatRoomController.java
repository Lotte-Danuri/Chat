package com.lotte.danuri.messengeron.controller;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.lotte.danuri.messengeron.model.dto.Chat;
import com.lotte.danuri.messengeron.model.vo.ChatVo;
import com.lotte.danuri.messengeron.model.vo.ChatsVo;
import com.lotte.danuri.messengeron.service.ChatRoomService;
import com.lotte.danuri.messengeron.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("chatRoom")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ChatRoomController {

    @Autowired
    ChatRoomService chatRoomService;

    UserService userService;

    @PostMapping(value = "chat",produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "sendChat")
    ResponseEntity pushChat(@RequestBody ChatVo vo) throws FirebaseMessagingException {
        ObjectId id;
        if (vo.getId() == null) {
           id = userService.findRoomIdByUserId(vo.getSendTo(), vo.getSendBy()).getChatRoomId();
        } else {
            id= new ObjectId(vo.getId());
        }

        chatRoomService.pushChat(id, vo.getSendTo(), vo.getChat());
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping(value = "chats",produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "sendChats")
    ResponseEntity pushChats(@RequestBody ChatsVo vo) throws FirebaseMessagingException {
        chatRoomService.pushChats(vo.getChatRoomIds(), vo.getChat());
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping(value = "chats/{userId}/{roomId}",produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "getChats")
    ResponseEntity<List<Chat>> getChats(@PathVariable String userId, @PathVariable String roomId) {

        return new ResponseEntity<>(chatRoomService.getChats(userId, new ObjectId(roomId)), HttpStatus.OK);
    }

    @GetMapping(value = "newChats/{userId}/{roomId}",produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "getNewChats")
    ResponseEntity<List<Chat>> getNewChats(@PathVariable String userId, @PathVariable String roomId) {
        return new ResponseEntity<>(chatRoomService.getNewChats(userId, new ObjectId(roomId)), HttpStatus.OK);
    }

    @PostMapping(value = "image",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
    @ApiOperation(value = "postImage")
    ResponseEntity pushImage(@RequestPart ChatVo chatVo, @RequestPart MultipartFile imageFile) throws FirebaseMessagingException {
        Chat chat = chatVo.getChat();
        chat.setSource(chatRoomService.pushImage(imageFile));
        ObjectId id;
        if (chatVo.getId() == null) {
            id = userService.findRoomIdByUserId(chatVo.getSendTo(), chatVo.getSendBy()).getChatRoomId();
        } else {
            id= new ObjectId(chatVo.getId());
        }

        chatRoomService.pushChat(id,chatVo.getSendTo(),chat);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
