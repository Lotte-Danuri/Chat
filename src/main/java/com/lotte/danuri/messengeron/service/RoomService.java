package com.lotte.danuri.messengeron.service;

import com.lotte.danuri.messengeron.dto.Room;

import java.util.List;

public interface RoomService {

    List<Room> findRoomsByUserIdOrderByLastActivityDesc(String userId);
}
