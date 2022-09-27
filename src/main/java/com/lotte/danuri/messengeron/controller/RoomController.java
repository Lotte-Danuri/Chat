package com.lotte.danuri.messengeron.controller;

import com.lotte.danuri.messengeron.model.dto.Chat;
import com.lotte.danuri.messengeron.model.dto.RoomData;
import com.lotte.danuri.messengeron.service.RoomService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("room")
public class RoomController {


    private final RoomService roomService;

    @GetMapping("/{userId}")
    @ApiOperation(value = "findRoomDatasByUserId")
    public ResponseEntity<List<Chat>> findRoomDatasByUserId(@PathVariable final String userId) {
        return new ResponseEntity<>(roomService.findRoomDatasByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/{userId}/{receiverId}")
    @ApiOperation(value = "findRoomIdByUserId")
    ResponseEntity<RoomData> findRoomIdByUserId(@PathVariable String userId, @PathVariable String receiverId) {
        return new ResponseEntity<>(roomService.findRoomIdByUserId(userId, receiverId), HttpStatus.OK);

    }

    @DeleteMapping("/{userId}")
    @ApiOperation(value = "deleteRoomData")
    public ResponseEntity deleteRoomData(@PathVariable final String userId, @RequestParam final ObjectId roomId) {
        roomService.deleteRoomData(userId, roomId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("")
    @ApiOperation(value = "createUser")
    public ResponseEntity createUser(String userId) {
        roomService.createUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
