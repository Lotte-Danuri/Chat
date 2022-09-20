package com.lotte.danuri.messengeron.service;

import com.lotte.danuri.messengeron.dto.Chat;
import com.lotte.danuri.messengeron.dto.Message;
import org.bson.types.ObjectId;

import java.util.List;

public interface ChatService {
    public ObjectId createChat();

    public void pushMessage(Chat chat, Message message);

    public boolean closeChat(ObjectId roomId);

    public List<Message> getMessages(ObjectId roomId);

    public boolean validChat(ObjectId roomId);

}
