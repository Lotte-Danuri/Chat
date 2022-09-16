package com.lotte.danuri.messengeron.service.serviceImpl;


import com.lotte.danuri.messengeron.dto.Room;
import com.lotte.danuri.messengeron.dto.RoomData;
import com.lotte.danuri.messengeron.repository.RoomDao;
import com.lotte.danuri.messengeron.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomDao roomDao;

    public Room findRoomsByUserId(String userId) {
        Room room = roomDao.findRoomsByUserId(userId);
        return room;
    }

    //채팅방 생성시 기존 채팅방이 존재하지 않는다면 생성후 채팅방 ID가 리턴
    public  RoomData upsertRoom(String userId, String receiverId){
        RoomData roomData = roomDao.findRoomIdByUserId(userId, receiverId);
        //if(roomData != null)
            return roomData;
        //else return createRoom(userId, receiverId);
    }

/*    public RoomData createRoom(String userId, String receiverId){
        return roomDao.createRoom(userId, receiverId);
    }*/

    public RoomData findRoomId(String userId, String receiverId){
        return roomDao.findRoomIdByUserId(userId, receiverId);
    }
}