package com.lotte.danuri.messengeron.service.serviceImpl;


import com.lotte.danuri.messengeron.dto.Chat;
import com.lotte.danuri.messengeron.dto.Room;
import com.lotte.danuri.messengeron.dto.RoomData;
import com.lotte.danuri.messengeron.repository.RoomDao;
import com.lotte.danuri.messengeron.service.ChatService;
import com.lotte.danuri.messengeron.service.RoomService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {


    @Autowired
    ChatService chatService;
    @Autowired
    private RoomDao roomDao;

    public Room findRoomsByUserId(String userId) {
        Room room = roomDao.findRoomsByUserId(userId);
        return room;
    }

    //싱데방과 채팅방 생성시 기존 채팅방이 존재하지 않는다면 생성후 채팅방 ID가 리턴
    public  List<RoomData> upsertRoom(String userId, String receiverId){
        List<RoomData> roomDatas = findRoomId(userId, receiverId);
        if (roomDatas == null) {
            RoomData roomData = new RoomData(createChatRoom(userId, receiverId),receiverId);
            roomDatas.add(roomData);
            return roomDatas;
        }
            return roomDatas;
    }



    public List<RoomData> findRoomId(String userId, String receiverId){
        return roomDao.findRoomIdByUserId(userId, receiverId);
    }

    public ObjectId createChatRoom(String userId, String receiverId){

        ObjectId roomId = chatService.createChat();

        //샌더에게 방만들기
        ObjectId id = roomDao.createChatRoom(userId, receiverId, roomId);

        //반는 사람에게 방만들기
        return roomDao.createChatRoom(receiverId, userId, id);
    }
}