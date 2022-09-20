package com.lotte.danuri.messengeron.service;

import com.lotte.danuri.messengeron.dto.Room;
import com.lotte.danuri.messengeron.dto.RoomData;
import org.bson.types.ObjectId;

import java.util.List;


public interface RoomService {

    Room findRoomsByUserId(String userId);;

    RoomData createChatRoom(String userId, String receiverId);

    boolean deleteRoomData(String userId, RoomData roomData);

    RoomData findRoomIdByUserId(String userId, String receiverId);

    Room createRoom(String userId);

    }
