package com.lotte.danuri.messengeron.service;

import com.lotte.danuri.messengeron.dto.Room;
import com.lotte.danuri.messengeron.dto.RoomData;
import org.bson.types.ObjectId;

import java.util.List;


public interface RoomService {

    Room findRoomsByUserId(String userId);

    List<RoomData> findRoomId(String userId, String receiverId);

    ObjectId createChatRoom(String userId, String receiverId);

    }
