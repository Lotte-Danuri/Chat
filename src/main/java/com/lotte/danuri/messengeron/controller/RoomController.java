package com.lotte.danuri.messengeron.controller;

import com.lotte.danuri.messengeron.model.dto.RoomData;
import com.lotte.danuri.messengeron.service.RoomService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("room")
public class RoomController {


    private final RoomService roomService;

    @GetMapping("/{userId}")
    @ApiOperation(value = "findRoomsByUserId")
    public List<RoomData> findRoomDatasByUserId(@PathVariable final String userId) {
        return roomService.findRoomDatasByUserId(userId);
    }

    @GetMapping("/{userId}/{receiverId}")
    @ApiOperation(value = "findRoomIdByUserId")
    public RoomData findRoomIdByUserId(@PathVariable final String userId, @PathVariable final String receiverId) {
        return roomService.findRoomIdByUserId(userId, receiverId);
    }

    @DeleteMapping("/{userId}")
    @ApiOperation(value = "deleteRoomData")
    public void deleteRoomData(@PathVariable final String userId, @RequestParam final ObjectId roomId) {
        roomService.deleteRoomData(userId, roomId);
    }

    @PostMapping("")
    @ApiOperation(value = "createUser")
    public void createUser(String userId) {
        roomService.createUser(userId);
    }
}
