package com.lotte.danuri.messengeron.repository;

import com.lotte.danuri.messengeron.dto.Chat;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatMongoDBRepository extends MongoRepository<Chat,ObjectId> {


}
