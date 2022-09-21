package com.lotte.danuri.messengeron.service;

import com.lotte.danuri.messengeron.model.dto.Room;
import com.lotte.danuri.messengeron.model.dto.RoomData;
import org.bson.types.ObjectId;

import java.util.List;


public interface RoomService {

    Room findRoomByUserId(String userId);

    RoomData createChatRoom(String userId, String receiverId);

    void deleteRoomData(String userId, ObjectId roomId);

    RoomData findRoomIdByUserId(String userId, String receiverId);

    Room createUser(String userId);

    List<RoomData> findRoomDatasByUserId(String userId);
}
