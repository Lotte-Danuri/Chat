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


@Repository
public class ChatDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void createChat(Chat chat) {
        mongoTemplate.insert(chat, "chat");
    }

    //채팅방에 메세지 삽입하는 메소드
    public void pushChat(Message message, ObjectId RoomId) {
        Query query = Query.query(Criteria.where("_jd").is(RoomId));

        Update update = new Update();

        update.push("messageList").each(message);

        mongoTemplate.updateFirst(query, update, "chat");
    }



}
