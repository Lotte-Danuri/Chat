package com.lotte.danuri.messengeron.service.serviceImpl;


import com.lotte.danuri.messengeron.exception.RoomDuplicationException;
import com.lotte.danuri.messengeron.exception.RoomNotFoundException;
import com.lotte.danuri.messengeron.model.dto.Chat;
import com.lotte.danuri.messengeron.model.dto.Room;
import com.lotte.danuri.messengeron.model.dto.RoomData;
import com.lotte.danuri.messengeron.model.vo.RoomListVo;
import com.lotte.danuri.messengeron.repository.ChatDao;
import com.lotte.danuri.messengeron.repository.RoomDao;
import com.lotte.danuri.messengeron.service.ChatService;
import com.lotte.danuri.messengeron.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class RoomServiceImpl implements RoomService {

    @Autowired
    private ChatDao chatDao;
    @Autowired
    private RoomDao roomDao;

    public Room findRoomByUserId(String userId) {
        return roomDao.findRoomByUserId(userId);
    }

    //상대방과 채팅할 수 있는 방정보 확인 후 방정보 리턴
    public RoomData findRoomIdByUserId(String userId, String receiverId) {
        if (roomDao.validRoom(userId) && roomDao.validRoom(receiverId)) {

            List<RoomData> roomDatas = getRoomList(userId);

            if (roomDatas.isEmpty())
                return createChatRoom(userId, receiverId);
            ListIterator<RoomData> roomIt = roomDatas.listIterator(roomDatas.size());

            while (roomIt.hasPrevious()) {
                RoomData roomData = roomIt.previous();
                if (roomData.getReceiverId().equals(receiverId))
                    if (chatDao.validChat(roomData.getRoomId()))
                        return roomData;

                    else return createChatRoom(userId, receiverId);

            }
            return createChatRoom(userId, receiverId);
        }else{
            throw new RoomNotFoundException("존재하지 않는 사용자 입니다.");
        }

    }

    @Override
    public Room createUser(String userId) {
        if(roomDao.userExists(userId))throw new RoomDuplicationException("이미 존재하는 사용자 입니다.");
        return roomDao.createUser(userId);
    }

    @Override
    public List<RoomListVo> findRoomDatasByUserId(String userId) {
        List<RoomListVo> chatRoomList = new ArrayList<>();

        for(RoomData roomData : getRoomList(userId)) {
            chatRoomList.add(new RoomListVo(chatDao.findChatRoomData(roomData.getRoomId()), roomData.getReceiverId(), chatDao.getNewMessages(roomData.getRoomId(), roomData.getLastWatched()).orElseGet(ArrayList::new).size()));
        }
        return chatRoomList.stream().sorted((o1, o2) -> o2.getChat().getUpdateAt().compareTo(o1.getChat().getUpdateAt())).collect(Collectors.toList());

    }

    private ArrayList<RoomData> getRoomList(String userId) {
        return findRoomByUserId(userId).getRoomList();
    }


    //채팅방 생성 후 방정보 넘겨줌
    public RoomData createChatRoom(String userId, String receiverId) {

        // 채팅방 생성
        ObjectId roomId = createRoom();

        //반는 사람에게 방정보 주기
        roomDao.createChatRoom(receiverId, userId, roomId);

        //보낸 사람에게 방정보 주기
        roomDao.createChatRoom(userId, receiverId, roomId);

        return new RoomData(roomId, receiverId, LocalDateTime.now());
    }

    public ObjectId createRoom(){
        Chat chat = new Chat();
        chat.setRoomType("chat");
        chat.setValid(true);
        chat.setMessageList(new ArrayList<>());
        chat.setUpdateAt(LocalDateTime.now());

        return chatDao.createChat(chat);
    }

    //방목록에 있는 방정보 삭제
    @Override
    public void deleteRoomData(String userId, ObjectId roomId) {
        chatDao.closeChat(roomId);
        roomDao.deleteRoomData(userId, roomId);
    }

    @Override
    public void updateRoomDataLastWatch(String userId, ObjectId roomId) {
        roomDao.updateRoomDataLastWatch(userId, roomId);
    }


}