package com.lotte.danuri.messengeron.service.serviceImpl;


import com.lotte.danuri.messengeron.model.dto.Room;
import com.lotte.danuri.messengeron.model.dto.RoomData;
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
import java.util.ListIterator;

@Service
@Transactional
public class RoomServiceImpl implements RoomService {


    @Autowired
    ChatService chatService;
    @Autowired
    private RoomDao roomDao;

    public Room findRoomByUserId(String userId) {
        return roomDao.findRoomByUserId(userId);
    }

    //상대방과 채팅할 수 있는 방정보 확인 후 방정보 리턴
    public RoomData findRoomIdByUserId(String userId, String receiverId) {
        List<RoomData> roomDatas = findRoomByUserId(userId).getRoomList();

        if (roomDatas.isEmpty())
            return createChatRoom(userId, receiverId);
        ListIterator<RoomData> roomIt = roomDatas.listIterator(roomDatas.size());

        while (roomIt.hasPrevious()) {
            RoomData roomData = roomIt.previous();
            if (roomData.getReceiverId().equals(receiverId))
                if (chatService.validChat(roomData.getRoomId()))
                    return roomData;

                else return createChatRoom(userId, receiverId);

        }
        return createChatRoom(userId, receiverId);
    }

    @Override
    public Room createUser(String userId) {
        return roomDao.createUser(userId);
    }

    @Override
    public List<RoomData> findRoomDatasByUserId(String userId) {
        return findRoomByUserId(userId).getRoomList();
    }

    //채팅방 생성 후 방정보 넘겨줌
    public RoomData createChatRoom(String userId, String receiverId) {

        // 채팅방 생성
        ObjectId roomId = chatService.createChat();

        //반는 사람에게 방정보 주기
        roomDao.createChatRoom(receiverId, userId, roomId);

        //보낸 사람에게 방정보 주기
        roomDao.createChatRoom(userId, receiverId, roomId);

        return new RoomData(roomId, receiverId);
    }

    //방목록에 있는 방정보 삭제
    @Override
    public void deleteRoomData(String userId, ObjectId roomId) {
        chatService.closeChat(roomId);
        roomDao.deleteRoomData(userId, roomId);
    }


}