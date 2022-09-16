package com.lotte.danuri.messengeron.repository;

import com.lotte.danuri.messengeron.dto.Room;
import com.lotte.danuri.messengeron.dto.RoomData;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.swing.text.Document;

import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@ExtendWith(SpringExtension.class)
class RoomDaoTest {

    private RoomDao dao;
    private Query query;


    @Test
    public void test(@Autowired MongoTemplate mongoTemplate) {



        Query query = Query.query(
                Criteria.where(
                        "userId"
                ).is("user_id")
        );
        Room doc = mongoTemplate.findOne(
                query, Room.class
        );
        Iterator<RoomData> roomDataLt = doc.getRoomList().iterator();

        while (roomDataLt.hasNext()) {
            RoomData roomData = roomDataLt.next();
            if(roomData.getReceiverId().equals("Test2"))
                System.out.println(roomData);
        }
    }
}