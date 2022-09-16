package com.lotte.danuri.messengeron.controller;

import com.lotte.danuri.messengeron.dto.Room;
import com.lotte.danuri.messengeron.dto.RoomData;
import com.lotte.danuri.messengeron.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("room")
public class RoomController {


    private final RoomService roomService;

    @GetMapping("/rooms/{userId}")
    public Room findRoomsByUserId(@PathVariable final String userId) {
        return roomService.findRoomsByUserId(userId);
    }

    @GetMapping("/room/{userId}/{receiverId}")
    public RoomData findRoomIdByUserId(@PathVariable final String userId, @PathVariable final String receiverId) {
        return roomService.findRoomId(userId, receiverId);
    }
}
