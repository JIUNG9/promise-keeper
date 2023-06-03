package com.studygroup.controller;


import com.studygroup.config.RabbitMqConfig;
import com.studygroup.dto.MessageDto;
import com.studygroup.entity.ChatMessage;
import com.studygroup.entity.ChatRoom;
import com.studygroup.entity.Member;
import com.studygroup.exception.ApiError;
import com.studygroup.service.SendMessageService;
import com.studygroup.service.chatroom.FindChatRoomByNameService;
import com.studygroup.service.chatroom.FindGroupChatRoomService;
import com.studygroup.service.chatroom.FindInquiryChatRoomService;
import com.studygroup.util.ChatRoomNameGenerator;
import com.studygroup.util.constant.ErrorCode;
import com.studygroup.util.constant.RoutingKeyGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ChatMessageController {

    private final FindChatRoomByNameService findChatRoomByNameService;
    private final SendMessageService sendMessageService;

    @PostMapping("/api/users/chats/inquiry-chats/{roomName}/messages")
    public ResponseEntity<Object> sendMessageToInquiryChatRoom(@PathVariable String roomName,
                                                               @RequestBody MessageDto message) {


        Optional<ChatRoom> chatRoom = findChatRoomByNameService.find(roomName);
        if (chatRoom.isPresent()) {
            log.info("Message:" + (message.toString()));
            ChatMessage chatMessage = ChatMessage.getMessage(message,chatRoom.get());
            String routingKey = RoutingKeyGenerator.inquiryRoutingKey(roomName);
            try {
                sendMessageService.sendMessage(routingKey,chatMessage);
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

        } else {
            return ApiError.
                    buildApiError(
                            ErrorCode.CHAT_ROOM_IS_NOT_EXISTED,
                            HttpStatus.NOT_FOUND);

        }
    }

    @PostMapping("/api/users/chats/groups/{groupName}/group-chats/messages")
    public ResponseEntity<Object> sendMessageToGroupChatRoom(@PathVariable String groupName,
                                                             @RequestBody MessageDto message) {

        String groupChatRoomName = ChatRoomNameGenerator.getGroupChatRoomName(groupName);
        Optional<ChatRoom> chatRoom = findChatRoomByNameService.find(groupChatRoomName);

        if (chatRoom.isPresent()) {
            log.info(message.toString());
            ChatMessage chatMessage = ChatMessage.getMessage(message, chatRoom.get());
            String routingKey = RoutingKeyGenerator.
                    groupChatRoomRoutingKey(groupChatRoomName);

            try {
                sendMessageService.sendMessage(routingKey,chatMessage);
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
        } else {
            return ApiError.
                    buildApiError(
                            ErrorCode.CHAT_ROOM_IS_NOT_EXISTED,
                            HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/api/users/chats/groups/{groupName}/live-group-chats/messages")
    public ResponseEntity<Object> sendMessageToLiveGroupChatRoom(@PathVariable String groupName,
                                                                 @RequestBody MessageDto message) {
        String liveChatRoomName = ChatRoomNameGenerator.getLiveChatRoomName(groupName);

        Optional<ChatRoom> chatRoom = findChatRoomByNameService.find(liveChatRoomName);
        if (chatRoom.isPresent()) {
            log.info("Message:" + (message.toString()));
            ChatMessage chatMessage = ChatMessage.getMessage(message, chatRoom.get());
            String routingKey =
                    RoutingKeyGenerator.
                    groupChatRoomRoutingKey(liveChatRoomName);

            try {
               sendMessageService.sendMessage(routingKey,chatMessage);
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
        else {
            return ApiError.
                    buildApiError(
                            ErrorCode.CHAT_ROOM_IS_NOT_EXISTED,
                            HttpStatus.NOT_FOUND);
        }
    }
}