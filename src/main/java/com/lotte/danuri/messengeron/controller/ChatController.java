package com.lotte.danuri.messengeron.controller;


import com.lotte.danuri.messengeron.model.dto.RoomData;
import com.lotte.danuri.messengeron.service.ChatService;
import com.lotte.danuri.messengeron.service.RoomService;
import com.lotte.danuri.messengeron.model.vo.MessageVo;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("chat")
public class ChatController {

    @Autowired
    RoomService roomService;
    @Autowired
    ChatService chatService;


    @PostMapping("")
    @ApiOperation(value = "pushMessage")
    ResponseEntity pushMessage(@RequestBody MessageVo vo) {
        chatService.pushMessage(vo.getRoomId(), vo.getMessage());
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("{userId}/{receiverId}")
    @ApiOperation(value = "findRoomIdByUserId")
    ResponseEntity<RoomData> findRoomIdByUserId(@PathVariable String userId, @PathVariable String receiverId) {
        return new ResponseEntity<>(roomService.findRoomIdByUserId(userId, receiverId), HttpStatus.OK);

    }
}
