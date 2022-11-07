package com.lotte.danuri.messengeron.controller;

import com.lotte.danuri.messengeron.model.dto.RoomData;
import com.lotte.danuri.messengeron.model.vo.RoomListVo;
import com.lotte.danuri.messengeron.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {


    private final UserService userService;

    @GetMapping(value = "/{userId}",produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "findRoomDatasByUserId")
    public ResponseEntity<List<RoomListVo>> findRoomDatasByUserId(@PathVariable final String userId) {
        return new ResponseEntity<>(userService.findRoomDatasByUserId(userId), HttpStatus.OK);
    }

    @GetMapping(value = "/{userId}/{receiverId}" ,produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "findRoomIdByUserId" )
    ResponseEntity<RoomData> findRoomIdByUserId(@PathVariable final String userId, @PathVariable final String receiverId) {
        return new ResponseEntity<>(userService.findRoomIdByUserId(userId, receiverId), HttpStatus.OK);

    }

    @PostMapping(value = "" ,produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "createUser")
    public ResponseEntity createUser(@RequestBody HashMap<String, String> map) {
        userService.createUser(map.get("userId"),map.get("userName"));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/fcmToken" ,produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Insert FCMToken")
    public ResponseEntity insertFCMToken(@RequestBody HashMap<String,String> map) {
        userService.insertFCMToken(map.get("userId"),map.get("fcmToken"));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{userId}/{roomId}",produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "deleteRoomData")
    public ResponseEntity deleteRoomData(@PathVariable final String userId,@PathVariable final String roomId) {
        userService.deleteRoomData(userId, new ObjectId(roomId));
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
