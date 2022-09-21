package com.lotte.danuri.messengeron.service;

import com.lotte.danuri.messengeron.model.dto.Message;
import org.bson.types.ObjectId;

import java.util.List;

public interface ChatService {
    public ObjectId createChat();

    public void pushMessage(ObjectId roomId, Message message);

    public void closeChat(ObjectId roomId);

    public List<Message> getMessages(ObjectId roomId);

    public boolean validChat(ObjectId roomId);

}
