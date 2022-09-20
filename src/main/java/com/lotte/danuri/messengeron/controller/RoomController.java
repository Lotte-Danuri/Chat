package com.lotte.danuri.messengeron.controller;

import com.lotte.danuri.messengeron.dto.Room;
import com.lotte.danuri.messengeron.dto.RoomData;
import com.lotte.danuri.messengeron.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


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
        return roomService.findRoomIdByUserId(userId, receiverId);
    }

    @DeleteMapping("/room/{userId}/")
    public boolean deleteRoomData(@PathVariable final String userId, @RequestParam final RoomData roomData) {
        return roomService.deleteRoomData(userId, roomData);
    }

    @PostMapping("/room")
    public void createRoom(@RequestParam final String userId) {
        roomService.createRoom(userId);
    }
}
