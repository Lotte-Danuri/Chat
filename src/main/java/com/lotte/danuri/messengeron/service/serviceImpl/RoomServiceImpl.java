package com.lotte.danuri.messengeron.service.serviceImpl;


import com.lotte.danuri.messengeron.dto.Room;
import com.lotte.danuri.messengeron.repository.RoomMongoDBRepository;
import com.lotte.danuri.messengeron.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomMongoDBRepository roomRepository;

    public List<Room> findRoomsByUserIdOrderByLastActivityDesc(String userId) {

        List<Room> rooms = roomRepository.findRoomsByUserIdOrderByLastActivityDesc(userId).orElseGet(ArrayList::new);

        return rooms;
    }

}
