package com.lotte.danuri.messengeron.service.serviceImpl;

import com.lotte.danuri.messengeron.config.util.S3Upload;
import com.lotte.danuri.messengeron.model.dto.Message;
import com.lotte.danuri.messengeron.repository.ChatDao;
import com.lotte.danuri.messengeron.repository.RoomDao;
import com.lotte.danuri.messengeron.service.ChatService;
import com.lotte.danuri.messengeron.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
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


    private final S3Upload s3Upload;
    private ChatDao chatDao;

    private RoomService roomService;


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
    public List<Message> getMessages(ObjectId roomId) {
        return chatDao.getMessages(roomId).orElseGet(ArrayList::new);
    }

    @Override
    public List<Message> getNewMessages(String userId, ObjectId roomId, LocalDateTime createdAt) {

        roomDao.updateRoomDataLastWatch(userId, roomId);
/*        List<Message> newMessages = new ArrayList<Message>();
        List<Message> messages = getMessages(roomId);
        ListIterator<Message> messageListIterator =
                messages.listIterator(messages.size());
        while (messageListIterator.hasPrevious()) {
            Message message = messageListIterator.previous();
            if (message.getMessageId().getDate().compareTo(messageId.getDate()) > 0) {
                newMessages.add(message);
            } else return newMessages;
        }*/
        List<Message> reMsgs = new ArrayList<Message>();
        List<Message> msgs = chatDao.getNewMessages(roomId).orElseThrow();
        ListIterator<Message> ll = msgs.listIterator(msgs.size());
        while (ll.hasPrevious()) {
            Message msg = ll.previous();
            if(msg.getCreatedAt().compareTo(createdAt) > 0) {
                reMsgs.add(msg);
            } else{
                return reMsgs;
            }
        }

        return null;
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
