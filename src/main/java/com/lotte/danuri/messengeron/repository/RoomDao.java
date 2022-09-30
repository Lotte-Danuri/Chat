package com.lotte.danuri.messengeron.repository;

import com.lotte.danuri.messengeron.model.dto.Room;
import com.lotte.danuri.messengeron.model.dto.RoomData;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;


@Repository
public class RoomDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    public Room createUser(String userId) {
        Room room = new Room();
        room.setUserId(userId);
        room.setRoomList(new ArrayList<RoomData>());
        return mongoTemplate.insert(room, "room");
    }

    public boolean userExists(String userId) {
        return mongoTemplate.exists(Query.query(Criteria.where("_id").is(userId)), "room");
    }

    public boolean createChatRoom(String userId, String receiverId, ObjectId roomId) {

        Update updateRoomList = new Update();

        RoomData sendRoomData = new RoomData();

        Query query = Query.query(Criteria.where("_id").is(userId));

        sendRoomData.setReceiverId(receiverId);
        sendRoomData.setRoomId(roomId);
        sendRoomData.setLastWatched(LocalDateTime.now());

        updateRoomList.push("roomList").each(sendRoomData);

        return mongoTemplate.upsert(query, updateRoomList, "room").wasAcknowledged();
    }

    public Room findRoomByUserId(String userId) {
        return mongoTemplate.findById(userId, Room.class, "room");
    }

    public void updateRoomDataLastWatch(String userId, ObjectId roomId) {
        Query query = Query.query(Criteria.where("_id").is(userId));
        query.addCriteria(Criteria.where("roomList._id").is(roomId));
        Update update = new Update();

        update.set("roomList.$.lastWatched", LocalDateTime.now());

        mongoTemplate.findAndModify(query, update, Room.class);
    }

    public LocalDateTime getLastWatched(String userId, ObjectId roomId) {
        Room room = mongoTemplate.findOne(Query.query(Criteria.where("userId").is(userId)), Room.class, "room");
        return room.getRoomList().stream().filter(s -> s.getRoomId().equals(roomId)).findFirst().orElseThrow(() -> new RuntimeException("Not Found RoomId")).getLastWatched();
    }

    //방목록에 있는 방정보 삭제
    public void deleteRoomData(String userId, ObjectId roomId) {
        Query query = Query.query(Criteria.where("_id").is(userId));

        Update updateRoomList = new Update();
        updateRoomList.pull("roomList", Query.query(Criteria.where("_id").is(roomId)));
        mongoTemplate.updateFirst(query, updateRoomList, "room");
    }

    public boolean validRoom(String userId) {
        return mongoTemplate.exists(Query.query(Criteria.where("_id").is(userId)), "room");
    }

}
