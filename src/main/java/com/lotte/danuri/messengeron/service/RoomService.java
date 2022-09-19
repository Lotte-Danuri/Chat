package com.lotte.danuri.messengeron.service;

import com.lotte.danuri.messengeron.dto.Room;
import com.lotte.danuri.messengeron.dto.RoomData;
import org.bson.types.ObjectId;


public interface RoomService {

    Room findRoomsByUserId(String userId);

    RoomData findRoomId(String userId, String receiverId);

    ObjectId createChatRoom(String userId, String receiverId, ObjectId roomId);

    }
