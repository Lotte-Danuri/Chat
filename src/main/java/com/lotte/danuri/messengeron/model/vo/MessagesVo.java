package com.lotte.danuri.messengeron.model.vo;

import com.lotte.danuri.messengeron.model.dto.Message;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public class MessagesVo {

    @NonNull
    private String contentType;
    @NonNull
    private String content;
    @NonNull
    private String sendBy;

    private String source;
    @NonNull
    private List<String> ids;



    public Message getMessage() {
        return new Message(new ObjectId(), this.getContentType(), this.getContent(), this.getSendBy(), this.getSource(), LocalDateTime.now());
    }

    public List<String> getRoomIds() {
        return ids;
    }

}
