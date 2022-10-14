package com.lotte.danuri.messengeron.service;


import com.lotte.danuri.messengeron.exception.RoomDuplicationException;
import com.lotte.danuri.messengeron.exception.RoomNotFoundException;
import com.lotte.danuri.messengeron.model.dto.ChatRoom;
import com.lotte.danuri.messengeron.model.dto.RoomData;
import com.lotte.danuri.messengeron.model.dto.User;
import com.lotte.danuri.messengeron.model.vo.RoomListVo;
import com.lotte.danuri.messengeron.repository.ChatDao;
import com.lotte.danuri.messengeron.repository.UserDao;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@RequiredArgsConstructor
@Service
public class UserService {

    private final ChatDao chatDao;
    private final UserDao userDao;

    public User findUserByUserId(String userId) {
        return userDao.findUserByUserId(userId);
    }

    public RoomData findRoomIdByUserId(String userId, String receiverId) {
        if (userDao.validUser(userId) && userDao.validUser(receiverId)) {

            List<RoomData> roomDatas = getRoomList(userId);

            if (roomDatas.isEmpty())
                return createChatRoom(userId, receiverId);
            ListIterator<RoomData> roomIt = roomDatas.listIterator(roomDatas.size());

            while (roomIt.hasPrevious()) {
                RoomData roomData = roomIt.previous();
                if (roomData.getReceiverId().equals(receiverId))
                    if (chatDao.validChat(roomData.getChatRoomId()))
                        return roomData;

                else return createChatRoom(userId, receiverId);

            }
            return createChatRoom(userId, receiverId);
        } else {
            throw new RoomNotFoundException("존재하지 않는 사용자 입니다.");
        }

    }


    public void createUser(String userId) {
        if (userDao.userExists(userId)) throw new RoomDuplicationException("이미 존재하는 사용자 입니다.");
        userDao.createUser(userId);
    }

    public List<RoomListVo> findRoomDatasByUserId(String userId) {
        List<RoomListVo> chatRoomList = new ArrayList<>();

        for (RoomData roomData : getRoomList(userId)) {
            chatRoomList.add(new RoomListVo(chatDao.findChatRoomData(roomData.getChatRoomId()), roomData.getReceiverId(), chatDao.getCountNewChats(roomData.getChatRoomId(), roomData.getLastWatched())));
        }
        return chatRoomList.stream().sorted((o1, o2) -> o2.getUpdateAt().compareTo(o1.getUpdateAt())).toList();

    }

    private ArrayList<RoomData> getRoomList(String userId) {
        try {
            return findUserByUserId(userId).getRoomList();
        } catch (RoomNotFoundException e) {
            return new ArrayList<>();
        }
    }


    //채팅방 생성 후 방정보 넘겨줌
    public RoomData createChatRoom(String userId, String receiverId) {

        // 채팅방 생성
        ObjectId chatRoomId = createRoom();

        //반는 사람에게 방정보 주기
        userDao.createChatRoom(receiverId, userId, chatRoomId);

        //보낸 사람에게 방정보 주기
        userDao.createChatRoom(userId, receiverId, chatRoomId);

        return new RoomData(chatRoomId, receiverId, LocalDateTime.now());
    }

    public ObjectId createRoom() {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setRoomType("chat");
        chatRoom.setValid(true);
        chatRoom.setChatList(new ArrayList<>());
        chatRoom.setUpdateAt(LocalDateTime.now());

        return chatDao.createChat(chatRoom);
    }

    //방목록에 있는 방정보 삭제
    public void deleteRoomData(String userId, ObjectId roomId) {
        chatDao.closeChatRoom(roomId);
        userDao.deleteRoomData(userId, roomId);
    }

    public void insertFCMToken(String userId, String fcmToken) {
        userDao.insertFCMToken(userId, fcmToken);
    }
}