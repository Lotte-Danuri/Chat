package com.lotte.danuri.messengeron.repository;

import com.lotte.danuri.messengeron.dto.Chat;
import com.lotte.danuri.messengeron.dto.Room;
import com.lotte.danuri.messengeron.dto.RoomData;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
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

import javax.swing.text.Document;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@ExtendWith(SpringExtension.class)
class RoomDaoTest {

    private RoomDao dao;
    private Query query;


    @Test
    public void test(@Autowired MongoTemplate mongoTemplate) {


        Update updateRoomList = new Update();

        RoomData roomData = new RoomData();

        Chat chat = new Chat();
        chat.setUpdateAt(LocalDateTime.now());
        chat.setLastMessage("");
        chat.setRoomType("chat");

        Query query = Query.query(Criteria.where("_id").is("user_id"));
        ObjectId id = mongoTemplate.insert(chat, "chat").getChatId();
        System.out.println(id);
        roomData.setRoomId(id);
        roomData.setReceiverId("Test2");
        updateRoomList.push("roomList").each(roomData);

        System.out.println(mongoTemplate.updateFirst(query, updateRoomList, "room").getUpsertedId());
    }

    @Test
    public void test2(@Autowired MongoTemplate mongoTemplate){


        Query query = Query.query(Criteria.where("_id").is("user_id"));

        Update updateRoomList = new Update();
        RoomData roomData = new RoomData();
        roomData.setRoomId(new ObjectId("6327e258cdff0e4bae5c428d"));
        roomData.setReceiverId("Test2");
        updateRoomList.pull("roomList",Query.query(Criteria.where("_id").is(roomData.getRoomId())));
        mongoTemplate.updateFirst(query, updateRoomList, "room");
    }
}