package com.studygroup.service.chatroom;


import com.studygroup.entity.ChatMessage;
import com.studygroup.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Log
public class ChatMessageListener {

    private final ChatMessageRepository chatMessageRepository;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @RabbitListener(queues = "inquiryQueue")
    public void receiveMessageFromInquiryRoom(ChatMessage chatMessage) {
        String inquiryChatRoomName = chatMessage.getChatRoom().getName();
        chatMessageRepository.save(chatMessage);
        log.info("Received message from the inquiry room: " + chatMessage.getPayload());
        simpMessagingTemplate.convertAndSend("/api/users/chats/" + inquiryChatRoomName);

    }

    @RabbitListener(queues = "groupQueue")
    public void receiveMessageFromGroupChatRoom(ChatMessage chatMessage) {
        String groupChatRoomName = chatMessage.getChatRoom().getName();
        chatMessageRepository.save(chatMessage);
        log.info("Received message from the group room: " + chatMessage.getPayload());

        simpMessagingTemplate.convertAndSend("/api/users/chats/groups/group-chat/" + groupChatRoomName);

    }

    @RabbitListener(queues = "liveGroupQueue")
    public void receiveMessageFromQueue1(ChatMessage chatMessage) {
        String liveGroupChatRoomName = chatMessage.getChatRoom().getName();
        chatMessageRepository.save(chatMessage);
        log.info("Received message from the live-group room: " + chatMessage.getPayload());

        simpMessagingTemplate.convertAndSend("/api/users/chats/groups/live-group-chat/" + liveGroupChatRoomName);
    }

}