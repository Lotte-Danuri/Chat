package com.lotte.danuri.messengeron.controller;

import com.lotte.danuri.messengeron.dto.Room;
import com.lotte.danuri.messengeron.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("room")
public class RoomController {

    @Autowired
    RoomService roomService;

    @GetMapping("/rooms")
    public List<Room> findRoomsByUserIdOrderByLastActivityDesc(String userId) {
        return roomService.findRoomsByUserIdOrderByLastActivityDesc(userId);
    }
}
