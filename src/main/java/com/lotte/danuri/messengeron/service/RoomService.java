package com.lotte.danuri.messengeron.service;

import com.lotte.danuri.messengeron.dto.Room;
import com.lotte.danuri.messengeron.dto.RoomData;

import java.util.List;

public interface RoomService {

    Room findRoomsByUserId(String userId);

    RoomData findRoomId(String userId, String receiverId);
}
