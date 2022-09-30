package com.lotte.danuri.messengeron.service.serviceImpl;

import com.lotte.danuri.messengeron.config.util.S3Upload;
import com.lotte.danuri.messengeron.model.dto.Message;
import com.lotte.danuri.messengeron.repository.ChatDao;
import com.lotte.danuri.messengeron.repository.RoomDao;
import com.lotte.danuri.messengeron.service.ChatService;
import com.lotte.danuri.messengeron.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    @Autowired

    private final S3Upload s3Upload;

    @Autowired
    private ChatDao chatDao;
    @Autowired

    private RoomService roomService;

    @Autowired

    private RoomDao roomDao;

    //메세지 보내기
    @Override
    public void pushMessage(ObjectId roomId, Message message) {
        chatDao.pushMessage(roomId, message);
    }


    //채팅방 유효성 확인    @Override
    public boolean validChat(ObjectId roomId) {
        return chatDao.validChat(roomId);
    }

    @Override
    public void pushMessages(List<String> userId, Message message) {
        userId.stream().map(id -> roomService.findRoomIdByUserId(message.getSendBy(), id).getRoomId()).forEach(roomId -> chatDao.pushMessage(roomId, message));
    }

    //메세지들 받아오기
    @Override
    public List<Message> getMessages(String userId,ObjectId roomId) {
        roomDao.updateRoomDataLastWatch(userId, roomId);

        return chatDao.getMessages(roomId).orElseGet(ArrayList::new);
    }

    @Override
    public List<Message> getNewMessages(String userId, ObjectId roomId) {
        List<Message> messages = chatDao.getNewMessages(roomId, roomDao.getLastWatched(userId,roomId)).orElseThrow();
        roomDao.updateRoomDataLastWatch(userId, roomId);
        return messages;
    }

    private String uploadImage(MultipartFile multipartFile){
        try {
            return s3Upload.upload(multipartFile);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
