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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class )
public class RoomServiceImpl implements RoomService {


    @Autowired
    ChatService chatService;
    @Autowired
    private RoomDao roomDao;

    public Room findRoomsByUserId(String userId) {
        Room room = roomDao.findRoomsByUserId(userId);
        return room;
    }

    //상대방과 채팅할 수 있는 방정보 확인 후 방정보 리턴
    public RoomData findRoomIdByUserId(String userId, String receiverId){
        Iterator<RoomData> roomIt = roomDao.findRoomsByUserId(userId).getRoomList().iterator();

        while (roomIt.hasNext()) {
            RoomData roomData = roomIt.next();
            if (roomData.getReceiverId().equals(receiverId) && chatService.validChat(roomData.getRoomId()))
                return roomData;

        }
         return createChatRoom(userId, receiverId);
    }

    @Override
    public Room createRoom(String userId) {
        return roomDao.createRoom(userId);
    }

    //채팅방 생성 후 방정보 넘겨줌
    public RoomData createChatRoom(String userId, String receiverId){

        // 채팅방 생성
        ObjectId roomId = chatService.createChat();

        //반는 사람에게 방정보 주기
        ObjectId id = roomDao.createChatRoom(receiverId, userId, roomId);

        //보낸 사람에게 방정보 주기
        roomDao.createChatRoom( userId,receiverId , id);

        return new RoomData(roomId, receiverId);
    }

    //방목록에 있는 방정보 삭제
    @Override
    public boolean deleteRoomData(String userId, RoomData roomData) {
        if(chatService.closeChat(roomData.getRoomId()))
            return roomDao.deleteRoomData(userId, roomData);
        return false;
    }


}