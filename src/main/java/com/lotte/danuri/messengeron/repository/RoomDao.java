package com.lotte.danuri.messengeron.repository;

import com.lotte.danuri.messengeron.dto.Room;
import com.lotte.danuri.messengeron.dto.RoomData;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Objects;

@Repository
public class RoomDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    public Room createRoom(String userId) {
        Room room = new Room();
        room.setUserId(userId);
        return (mongoTemplate.insert(room, "room"));
    }

    public ObjectId createChatRoom(String userId, String receiverId, ObjectId roomId) {

        Update updateRoomList = new Update();

        RoomData sendRoomData = new RoomData();

        Query query = Query.query(Criteria.where("_id").is(userId));

        sendRoomData.setReceiverId(receiverId);
        sendRoomData.setRoomId(roomId);

        updateRoomList.push("roomList").each(sendRoomData);

        return mongoTemplate.upsert(query, updateRoomList, "room").getUpsertedId().asObjectId().getValue();
    }

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

    public void deleteRoomData(String userId, RoomData roomData) {
        Query query = Query.query(Criteria.where("_id").is(userId));

        Update updateRoomList = new Update();
        updateRoomList.pull("roomList",Query.query(Criteria.where("_id").is(roomData.getRoomId())));
        mongoTemplate.updateFirst(query, updateRoomList, "room");
    }

    public void deleteRoom(String userId){
        Query query = Query.query(Criteria.where("_id").is(userId));
        mongoTemplate.remove(query, "room");
    }
}
