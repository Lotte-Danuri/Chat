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


@Repository
public class RoomDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    public Room createRoom(String userId) {
        Room room = new Room();
        room.setUserId(userId);
        return mongoTemplate.insert(room, "room");
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


    //방목록에 있는 방정보 삭제
    public boolean deleteRoomData(String userId, RoomData roomData) {
        Query query = Query.query(Criteria.where("_id").is(userId));

        Update updateRoomList = new Update();
        updateRoomList.pull("roomList",Query.query(Criteria.where("_id").is(roomData.getRoomId())));
        return mongoTemplate.updateFirst(query, updateRoomList, "room").wasAcknowledged();
    }

}
