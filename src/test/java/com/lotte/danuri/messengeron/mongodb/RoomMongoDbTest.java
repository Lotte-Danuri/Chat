package com.lotte.danuri.messengeron.mongodb;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DataMongoTest
@ExtendWith(SpringExtension.class)
public class RoomMongoDbTest {



    @Test
    public void test() {

//        Room aRoom = new Room("user_id", ["631ff7772e988a0650362fc4"], "2022-09-12T15:00:00.000+00:00");

        // given
        ObjectId id = new ObjectId();
        System.out.printf("test complete");
        //assertThat(roomMongoDBRepository.getRoomByUserId("user_id").getRoomList().get(0)).isEqualTo(id);
        // when
        // then

    }
}
