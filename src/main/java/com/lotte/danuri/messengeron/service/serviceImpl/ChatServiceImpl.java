package com.lotte.danuri.messengeron.service.serviceImpl;

import com.lotte.danuri.messengeron.model.dto.Chat;
import com.lotte.danuri.messengeron.model.dto.Message;
import com.lotte.danuri.messengeron.repository.ChatDao;
import com.lotte.danuri.messengeron.service.ChatService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatDao chatDao;
    @Override
    public ObjectId createChat() {
        Chat chat = new Chat();
        chat.setUpdateAt(LocalDateTime.now());
        chat.setLastMessage("");
        chat.setRoomType("chat");
        chat.setValid(true);
        return chatDao.createChat(chat);
    }



    //메세지 보내기
    @Override
    public void pushMessage(ObjectId roomId, Message message) {
        chatDao.pushMessage(roomId, message);
    }

    //채팅방 닫기
    @Override
    public void closeChat(ObjectId roomId) {
        chatDao.closeChat(roomId);
    }

    //채팅방 유효성 확인    @Override
    public boolean validChat(ObjectId roomId) {
        return chatDao.validChat(roomId);
    }

    //메세지들 받아오기
    @Override
    public List<Message> getMessages(ObjectId roomId) {
        return chatDao.getMessages(roomId).orElseGet(ArrayList::new);
    }


}
