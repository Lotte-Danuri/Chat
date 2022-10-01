package com.lotte.danuri.messengeron.service;

import com.lotte.danuri.messengeron.model.dto.Chat;
import com.lotte.danuri.messengeron.model.dto.Message;
import org.bson.types.ObjectId;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

public interface ChatService {

     void pushMessage(ObjectId roomId, Message message);

     List<Message> getMessages(String userId,ObjectId roomId);

     boolean validChat(ObjectId roomId);

    void pushMessages(List<String> roomIds, Message message);

    List<Message> getNewMessages(String userId, ObjectId roomId);

    String pushImage(MultipartFile multipartFile);

}
