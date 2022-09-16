package com.lotte.danuri.messengeron.repository;

import com.lotte.danuri.messengeron.dto.Room;
import com.lotte.danuri.messengeron.dto.RoomData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

@Repository
public class RoomDao {

    @Autowired
    private MongoTemplate mongoTemplate;


    public Room findRoomsByUserId(String userId) {
        return mongoTemplate.findById(userId, Room.class);
    }

    public RoomData findRoomIdByUserId(String userId, String receiverId) {

        Query query = Query.query(
                Criteria.where(
                        "userId"
                ).is(userId)
        );
        Room doc = mongoTemplate.findOne(
                query, Room.class
        );
        Iterator<RoomData> roomDataLt = doc.getRoomList().iterator();

        while (roomDataLt.hasNext()) {
            RoomData roomData = roomDataLt.next();
/*
            if(roomData.getReceiverId().equals(receiverId))
*/
            return roomData;
        }

        return null;
    }


/*    public RoomData createRoom(String userId, String receiverId) {
        room = new Room(null, );
        mongoTemplate.insert()
    }*/
}
