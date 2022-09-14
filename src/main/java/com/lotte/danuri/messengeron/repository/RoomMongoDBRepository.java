package com.lotte.danuri.messengeron.repository;

import com.lotte.danuri.messengeron.dto.Room;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;



public interface RoomMongoDBRepository extends MongoRepository<Room, String> {
    Optional<ArrayList<Room>> findRoomsByUserIdOrderByLastActivityDesc(String userId);
    Room findRoomByUserId(String userId);
}
