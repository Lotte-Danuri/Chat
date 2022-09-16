package com.lotte.danuri.messengeron.repository;

import com.lotte.danuri.messengeron.dto.Chat;
import com.lotte.danuri.messengeron.dto.Message;
import com.lotte.danuri.messengeron.dto.Room;
import com.lotte.danuri.messengeron.dto.RoomData;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@DataMongoTest
@ExtendWith(SpringExtension.class)
class ChatDaoTest {

    private ChatDao dao;
    private Query query;


    @Test
    public void test(@Autowired MongoTemplate mongoTemplate) {


        ArrayList<Message> messageList = new ArrayList<Message>();
        Message message = new Message("message", null, "messageDe", "user_id");
        messageList.add(message);
        Chat chat = new Chat(null, messageList,null );
        mongoTemplate.insert(chat, "chat");
    }

    @Test
    public void sendChat(@Autowired MongoTemplate mongoTemplate){

        Query query = new Query().addCriteria(Criteria.where( "_id" ).is( new ObjectId("6323ef67019e1b583e3260ad")) );

        Update update = new Update();

        List<Document> array = new ArrayList<>();  //데이터를 추가할 배열 입니다.

        Document item = new Document();  //배열안에 담을 값 입니다.
        Message msg = new Message();
        msg.setSendBy("Test2");
        msg.setContent("message");
        msg.setContentType("type");
        item.put("contentType", "type");


        update.push("list").each(msg);  //push라는 메소드에 키 값을 넣어주고 each를 통해서 해당 배열에 순차적으로 저장하게 하였습니다.
        System.out.println(mongoTemplate.updateFirst(query, update, "chat"));    }
}