package com.studygroup.controller;


import com.studygroup.config.RabbitMqConfig;
import com.studygroup.entity.ChatMessage;
import com.studygroup.entity.Member;
import com.studygroup.exception.ApiError;
import com.studygroup.util.ChatRoomNameGenerator;
import com.studygroup.util.constant.RoutingKeyGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ChatMessageController {
    private final RabbitTemplate rabbitTemplate;


    @PostMapping("/api/users/chats/{roomName}")
    public ResponseEntity<Object> sendMessageToInquiryChatRoom(@PathVariable String roomName, @RequestBody ChatMessage message) {


        log.info("Get message from the client : " + message.getPayload());
        try {
            rabbitTemplate.convertAndSend(
                    RabbitMqConfig.topicExchange,
                    RoutingKeyGenerator.inquiryRoutingKey(roomName),
                    message);
        } catch (AmqpException e) {
            return ApiError.buildApiError(
                    ApiError.
                    builder().
                            status(HttpStatus.INTERNAL_SERVER_ERROR).
                            code(7003).
                            message(e.getMessage()).
                            subErrors(null).
                            timestamp(LocalDateTime.now()).
                            build());
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/users/chats/groups/{groupName}/group-chat")
    public ResponseEntity<Object> sendMessageToGroupChatRoom(@PathVariable String groupName,
                                           @RequestBody ChatMessage message) {
        String groupChatRoomName = ChatRoomNameGenerator.getGroupChatRoomName(groupName);
        log.info("Get message from the client : " + message.getPayload());
        try {
            rabbitTemplate.convertAndSend(
                    RabbitMqConfig.topicExchange,
                    RoutingKeyGenerator.groupChatRoomRoutingKey(groupChatRoomName),
                    message);
        } catch (AmqpException e){
            return ApiError.buildApiError(
                    ApiError.
                            builder().
                            status(HttpStatus.INTERNAL_SERVER_ERROR).
                            code(7003).
                            message(e.getMessage()).
                            subErrors(null).
                            timestamp(LocalDateTime.now()).
                            build());
        }
        return ResponseEntity.ok().build();
    }


    @PostMapping("/api/users/chats/groups/{groupName}/live-group-chat")
    public ResponseEntity<Object> sendMessageToLiveGroupChatRoom(@PathVariable String groupName,
                                               @RequestBody ChatMessage message) {
        String liveChatRoomName = ChatRoomNameGenerator.getLiveChatRoomName(groupName);
        log.info("Get message from the client : " + message.getPayload());
        try {
            rabbitTemplate.convertAndSend(
                    RabbitMqConfig.topicExchange,
                    RoutingKeyGenerator.groupChatRoomRoutingKey(liveChatRoomName),
                    message);
        } catch (AmqpException e) {
            return ApiError.buildApiError(
                    ApiError.
                            builder().
                            status(HttpStatus.INTERNAL_SERVER_ERROR).
                            code(7003).
                            message(e.getMessage()).
                            subErrors(null).
                            timestamp(LocalDateTime.now()).
                            build());
        }
        return ResponseEntity.ok().build();
    }
}