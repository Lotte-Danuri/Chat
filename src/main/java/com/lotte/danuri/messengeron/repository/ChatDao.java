package com.lotte.danuri.messengeron.repository;

import com.lotte.danuri.messengeron.dto.Chat;
import com.lotte.danuri.messengeron.dto.Message;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

import java.util.List;


@Repository
public class ChatDao {

    @Autowired
    private MongoTemplate mongoTemplate;


    //채팅방 생성 메소드
    public ObjectId createChat(Chat chat) {
        return mongoTemplate.insert(chat, "chat").getChatId();
    }

    //채팅방에 메세지 삽입하는 메소드
    public void pushMessage(Chat chat, Message message) {
        Query query = Query.query(Criteria.where("_id").is(chat.getChatId()));

        Update updateChat = new Update();
        Update updateMessage = new Update();

        updateChat.set("updateAt", LocalDateTime.now());
        updateChat.set("lastMessage", message.getContent());
        updateMessage.push("messageList").each(message);

        mongoTemplate.updateFirst(query, updateChat,"chat");
        mongoTemplate.updateFirst(query, updateMessage, "chat");
    }

    //메세지 읽어오는 메소드
    public List<Message> getMessages(ObjectId roomId){
        List<Message> messages;

        Query query = Query.query(Criteria.where("_id").is(roomId));


        Chat chat =  mongoTemplate.findOne(query,Chat.class,"chat");

        messages = chat.getMessageList();

        return messages;
    }


}
