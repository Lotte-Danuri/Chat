package com.lotte.danuri.messengeron.service;

import com.lotte.danuri.messengeron.repository.RoomMongoDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService{


    private final RoomMongoDBRepository repository = new RoomMongoDBRepository();


}
