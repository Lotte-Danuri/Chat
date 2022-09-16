package com.lotte.danuri.messengeron.service.serviceImpl;

import com.lotte.danuri.messengeron.dto.Chat;
import com.lotte.danuri.messengeron.repository.ChatDao;
import com.lotte.danuri.messengeron.repository.RoomDao;
import com.lotte.danuri.messengeron.repository.RoomMongoDBRepository;
import com.lotte.danuri.messengeron.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatDao chatDao;
    @Override
    public void createChat(Chat chat) {

        chatDao.createChat(chat);
    }


    //private final RoomMongoDBRepository repository = new RoomMongoDBRepository();


}
