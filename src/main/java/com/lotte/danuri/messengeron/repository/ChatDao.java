package com.lotte.danuri.messengeron.repository;

import com.lotte.danuri.messengeron.model.dto.Chat;
import com.lotte.danuri.messengeron.model.dto.ChatRoom;
import com.lotte.danuri.messengeron.model.dto.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;


@Repository
public class ChatDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    public ChatRoom findChatRoomData(ObjectId id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        query.fields().slice("chatList", -1);
        return mongoTemplate.findOne(query, ChatRoom.class, "chatRoom");
    }

    //채팅방 생성 메소드
    public ObjectId createChat(ChatRoom chatRoom) {
        return mongoTemplate.insert(chatRoom, "chatRoom").getChatRoomId();
    }

    //채팅방에 메세지 삽입하는 메소드
    public void pushChat(ObjectId chatRoomId, Chat chat) {
        Query query = Query.query(Criteria.where("_id").is(chatRoomId));

        Update updateChatRoom = new Update();
        Update updateChat = new Update();

        updateChatRoom.set("updateAt", LocalDateTime.now());
        updateChat.push("chatList").each(chat);

        mongoTemplate.updateFirst(query, updateChatRoom, "chatRoom");
        mongoTemplate.updateFirst(query, updateChat, "chatRoom");
    }

    //메세지들을 읽어오는 메소드
    public Optional<List<Chat>> getChats(ObjectId chatRoomId) {


        Query query = Query.query(Criteria.where("_id").is(chatRoomId));


        ChatRoom chatRoom = mongoTemplate.findOne(query, ChatRoom.class, "chatRoom");

        Optional<List<Chat>> chatList = Optional.ofNullable(chatRoom.getChatList());

        return chatList;
    }

    public Optional<List<Chat>> getNewChats(ObjectId roomId, LocalDateTime lastWatched) {
        List<Chat> reChats = new ArrayList<>();

        Query query = Query.query(Criteria.where("_id").is(roomId));
        query.fields().include("chatList").exclude("_id");
        List<Chat> msgs = mongoTemplate.findOne(query, ChatRoom.class, "chatRoom").getChatList();
        if(msgs.size() == 0) {
            return Optional.of(reChats);
        }
        ListIterator<Chat> ll = msgs.listIterator(msgs.size());

        while (ll.hasPrevious()) {
            Chat chat = ll.previous();

            if(chat.getCreatedAt().compareTo(lastWatched) > 0) {

                reChats.add(chat);

            } else{
                return Optional.of(reChats);
            }
        }
        return Optional.ofNullable(reChats);
    }

    public int getCountNewChats(ObjectId roomId, LocalDateTime lastWatched) {
        return getNewChats(roomId, lastWatched).orElseGet(ArrayList::new).size();
    }


    //방 유효성 확인
    public boolean validChat(ObjectId chatId) {
        Query query = Query.query(Criteria.where("_id").is(chatId));
        return mongoTemplate.findOne(query, ChatRoom.class, "chatRoom").isValid();
    }

    //방 비활성화
    public void closeChatRoom(ObjectId roomId) {
        Query query = Query.query(Criteria.where("_id").is(roomId));

        Update update = new Update();

        update.set("valid", false);

        mongoTemplate.updateFirst(query, update, "chatRoom");
    }


}
