package com.lotte.danuri.messengeron.repository;

import com.lotte.danuri.messengeron.model.dto.User;
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


@Repository
public class UserDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void createUser(String userId) {
        User user = new User();
        user.setUserId(userId);
        user.setFcmToken(new ArrayList<>());
        user.setRoomList(new ArrayList<>());
        mongoTemplate.insert(user, "user");
    }

    public boolean userExists(String userId) {
        return mongoTemplate.exists(Query.query(Criteria.where("_id").is(userId)), "user");
    }

    public void createChatRoom(String userId, String receiverId, ObjectId roomId) {

        Update updateUserList = new Update();

        RoomData sendRoomData = new RoomData();

        Query query = Query.query(Criteria.where("_id").is(userId));

        sendRoomData.setReceiverId(receiverId);
        sendRoomData.setChatRoomId(roomId);
        sendRoomData.setLastWatched(LocalDateTime.now());

        updateUserList.push("roomList").each(sendRoomData);

        mongoTemplate.upsert(query, updateUserList, "user").wasAcknowledged();
    }

    public User findUserByUserId(String userId) {
        return mongoTemplate.findById(userId, User.class, "user");
    }

    public void updateRoomDataLastWatch(String userId, ObjectId roomId) {
        Query query = Query.query(Criteria.where("_id").is(userId));
        query.addCriteria(Criteria.where("roomList._id").is(roomId));
        Update update = new Update();

        update.set("roomList.$.lastWatched", LocalDateTime.now());

        mongoTemplate.findAndModify(query, update, User.class);
    }

    public LocalDateTime getLastWatched(String userId, ObjectId roomId) {
        User user = mongoTemplate.findOne(Query.query(Criteria.where("userId").is(userId)), User.class, "user");
        return user.getRoomList().stream().filter(s -> s.getChatRoomId().equals(roomId)).findFirst().orElseThrow(() -> new RuntimeException("Not Found UserId")).getLastWatched();
    }

    //방목록에 있는 방정보 삭제
    public void deleteRoomData(String userId, ObjectId roomId) {
        Query query = Query.query(Criteria.where("_id").is(userId));

        Update updateRoomList = new Update();
        updateRoomList.pull("roomList", Query.query(Criteria.where("_id").is(roomId)));
        mongoTemplate.updateFirst(query, updateRoomList, "user");
    }

    public boolean validUser(String userId) {
        return mongoTemplate.exists(Query.query(Criteria.where("_id").is(userId)), "user");
    }

    public void insertFCMToken(String userId, String fcmToken) {
        Query query = new Query(Criteria.where("_id").is(userId));
        Update update = new Update();
        update.push("fcmToken").each(fcmToken);
        mongoTemplate.updateFirst(query, update,"user");
    }
}
