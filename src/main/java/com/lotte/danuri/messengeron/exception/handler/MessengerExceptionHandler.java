package com.lotte.danuri.messengeron.exception.handler;

import com.lotte.danuri.messengeron.exception.RoomDuplicationException;
import com.lotte.danuri.messengeron.exception.RoomNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MessengerExceptionHandler {


    @ExceptionHandler(RoomNotFoundException.class)
    public ResponseEntity<String> roomNotFoundException(RoomNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RoomDuplicationException.class)
    public ResponseEntity<String> roomDuplicationException(RoomDuplicationException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
