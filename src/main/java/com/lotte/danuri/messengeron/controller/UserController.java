package com.lotte.danuri.messengeron.controller;

import com.lotte.danuri.messengeron.model.dto.RoomData;
import com.lotte.danuri.messengeron.model.vo.RoomListVo;
import com.lotte.danuri.messengeron.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {


    private final UserService userService;

    @GetMapping("/{userId}")
    @ApiOperation(value = "findRoomDatasByUserId")
    public ResponseEntity<List<RoomListVo>> findRoomDatasByUserId(@PathVariable final String userId) {
        return new ResponseEntity<>(userService.findUserDatasByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/{userId}/{receiverId}")
    @ApiOperation(value = "findRoomIdByUserId")
    ResponseEntity<RoomData> findRoomIdByUserId(@PathVariable String userId, @PathVariable String receiverId) {
        return new ResponseEntity<>(userService.findRoomIdByUserId(userId, receiverId), HttpStatus.OK);

    }

    @DeleteMapping("/{userId}")
    @ApiOperation(value = "deleteRoomData")
    public ResponseEntity deleteRoomData(@PathVariable final String userId, @RequestParam final ObjectId roomId) {
        userService.deleteRoomData(userId, roomId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("")
    @ApiOperation(value = "createUser")
    public ResponseEntity createUser(String userId, String fcmToken) {
        userService.createUser(userId, fcmToken);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
