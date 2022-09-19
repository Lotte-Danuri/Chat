package com.lotte.danuri.messengeron.service.serviceImpl;

import com.lotte.danuri.messengeron.dto.Chat;
import com.lotte.danuri.messengeron.dto.Message;
import com.lotte.danuri.messengeron.repository.ChatDao;
import com.lotte.danuri.messengeron.service.ChatService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatDao chatDao;
    @Override
    public ObjectId createChat() {
        Chat chat = new Chat();
        chat.setUpdateAt(LocalDateTime.now());
        chat.setLastMessage("");
        chat.setRoomType("chat");
        return chatDao.createChat(chat);
    }

    @Override
    public void pushMessage(Chat chat, Message message) {
        chatDao.pushMessage(chat, message);
    }

    @Override
    public List<Message> getMessages(ObjectId roomId) {
        return chatDao.getMessages(roomId);
    }


}
