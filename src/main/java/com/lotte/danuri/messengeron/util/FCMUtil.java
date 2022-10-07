package com.lotte.danuri.messengeron.util;

import com.google.firebase.messaging.*;
import com.lotte.danuri.messengeron.model.dto.Chat;

import java.util.List;

public class FCMUtil {
    public static void sendMessage(String token, Chat chat) throws FirebaseMessagingException {
        Message message = Message.builder().setWebpushConfig(WebpushConfig.builder().setNotification(
                        WebpushNotification.builder()
                                .setTitle("LuxON")
                                .setIcon("https://danuri-luxon.s3.ap-northeast-2.amazonaws.com/images/logo_black.png")
                                .setTimestampMillis(System.currentTimeMillis())
                                .setSilent(false)
                                .setBadge("https://danuri-luxon.s3.ap-northeast-2.amazonaws.com/images/logo_black.png")
                                .setVibrate(new int[]{1, 0, 1})
                                .setDirection(WebpushNotification.Direction.AUTO)
                                .setBody(chat.getSendBy() + "님이 보낸 " + chat.getContentType() + "\n" + chat.getContent())
                                .build()
                ).build())
                .putData("sendBy", chat.getSendBy())
                .putData("content", chat.getContent())
                .setToken(token)
                .build();


        String response = FirebaseMessaging.getInstance().send(message);
    }

    public static void sendMessages(List<String> tokens, Chat chat) throws FirebaseMessagingException {

        MulticastMessage message = MulticastMessage.builder().setWebpushConfig(WebpushConfig.builder().setNotification(
                        WebpushNotification.builder()
                                .setTitle("LuxON")
                                .setIcon("https://danuri-luxon.s3.ap-northeast-2.amazonaws.com/images/logo_black.png")
                                .setTimestampMillis(System.currentTimeMillis())
                                .setSilent(false)
                                .setBadge("https://danuri-luxon.s3.ap-northeast-2.amazonaws.com/images/logo_black.png")
                                .setVibrate(new int[]{1, 0, 1})
                                .setDirection(WebpushNotification.Direction.AUTO)
                                .setBody(chat.getSendBy() + "님이 보낸 " + chat.getContentType() + "\n" + chat.getContent())
                                .build()
                ).build())
                .putData("sendBy", chat.getSendBy())
                .putData("content", chat.getContent())
                .addAllTokens(tokens)
                .build();


        BatchResponse response = FirebaseMessaging.getInstance().sendMulticast(message);
    }
}
