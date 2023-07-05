package com.studygroup.service.chatroom;


import com.studygroup.domain.ChatMessage;
import com.studygroup.repository.ChatMessageRepository;
import com.studygroup.util.creator.ChatRoomNameGenerator;
import com.studygroup.util.constant.uri.ChatDestinationPrefixURI;
import com.studygroup.util.constant.uri.ServerUrl;
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

    simpMessagingTemplate.
        convertAndSend(
            ChatDestinationPrefixURI.INQUIRY_ROOM_URI +
                inquiryChatRoomName,
            chatMessage);
  }

  @RabbitListener(queues = "groupQueue")
  public void receiveMessageFromGroupChatRoom(ChatMessage chatMessage) {
    String groupChatRoomName = chatMessage.getChatRoom().getName();
    chatMessageRepository.save(chatMessage);
    log.info("Received message from the group room: " + chatMessage.getPayload());
    simpMessagingTemplate.
        convertAndSend(
            ServerUrl.FRONTEND_SERVER_DOMAIN +
                ChatDestinationPrefixURI.GROUP_ROOM_URI +
                groupChatRoomName,
            chatMessage);
  }

  @RabbitListener(queues = "liveGroupQueue")
  public void receiveMessageFromQueue1(ChatMessage chatMessage) {
    log.info("Received message from the live-group room: " + chatMessage.getPayload());

    String liveGroupChatRoomName = ChatRoomNameGenerator.getLiveChatRoomName(
        chatMessage.getChatRoom().getName());
    simpMessagingTemplate.
        convertAndSend(
            ServerUrl.FRONTEND_SERVER_DOMAIN +
                ChatDestinationPrefixURI.LIVE_GROUP_ROOM +
                liveGroupChatRoomName,
            chatMessage);


  }

}