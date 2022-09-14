package com.lotte.danuri.messengeron.mongodb;

import com.lotte.danuri.messengeron.dto.Chat;
import com.lotte.danuri.messengeron.dto.Message;
import com.lotte.danuri.messengeron.repository.ChatMongoDBRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@ExtendWith(SpringExtension.class)
public class ChatMongoDbTest {

    ChatMongoDBRepository chatMongoDBRepository;

    @Autowired
    public void setChatMongoDBRepository(ChatMongoDBRepository chatMongoDBRepository) {
        this.chatMongoDBRepository = chatMongoDBRepository;
    }

    @Test
    public void test() {

//        Room aRoom = new Room("user_id", ["631ff7772e988a0650362fc4"], "2022-09-12T15:00:00.000+00:00");

        // given
        ObjectId id = new ObjectId("631ff7772e988a0650362fc4");
        Message message = new Message("type", LocalDateTime.parse("2022-09-13T00:00"), "content", "user_id");
        Chat chat = new Chat(id, Arrays.asList(), LocalDateTime.parse("2022-09-13T00:00"));
        assertThat(chatMongoDBRepository.findById(id)).isEqualTo(chat);
        // when
        // then

    }
}
