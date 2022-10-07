package com.lotte.danuri.messengeron.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.lotte.danuri.messengeron.model.dto.Chat;
import com.lotte.danuri.messengeron.repository.UserDao;
import com.lotte.danuri.messengeron.util.FCMUtil;
import com.lotte.danuri.messengeron.util.S3Upload;
import com.lotte.danuri.messengeron.repository.ChatDao;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatRoomService {

    @Autowired

    private final S3Upload s3Upload;

    @Autowired
    private ChatDao chatDao;
    @Autowired

    private UserService userService;

    @Autowired
    private UserDao userDao;

    //메세지 보내기
    public void pushChat(ObjectId chatRoomId, String snedTo, Chat chat) throws FirebaseMessagingException {
        chatDao.pushChat(chatRoomId, chat);
        sendMessage(chat, snedTo);
    }


    //채팅방 유효성 확인    @Override
    public boolean validChat(ObjectId chatRoomId) {
        return chatDao.validChat(chatRoomId);
    }

    public void pushChats(List<String> userId, Chat chat) throws FirebaseMessagingException {
        for (String uId : userId) {
            pushChat(userService.findRoomIdByUserId(chat.getSendBy(), uId).getChatRoomId()
                    , uId
                    , chat
            );

        }
    }

    //메세지들 받아오기
    public List<Chat> getChats(String userId, ObjectId chatRoomId) {
        userDao.updateRoomDataLastWatch(userId, chatRoomId);

        return chatDao.getChats(chatRoomId).orElseGet(ArrayList::new);
    }

    public List<Chat> getNewChats(String userId, ObjectId chatRoomId) {
        List<Chat> chats = chatDao.getNewChats(chatRoomId, userDao.getLastWatched(userId, chatRoomId)).orElseThrow();
        userDao.updateRoomDataLastWatch(userId, chatRoomId);
        return chats;
    }

    public String pushImage(MultipartFile multipartFile) {
        try {
            return s3Upload.upload(multipartFile);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(Chat chat, String snedTo) throws FirebaseMessagingException {
        FCMUtil.sendMessage(userDao.findUserByUserId(snedTo).getFcmToken(), chat);
    }
}
