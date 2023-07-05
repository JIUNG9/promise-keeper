package com.studygroup.controller;


import com.studygroup.dto.MessageDto;
import com.studygroup.service.chat.SendMessageService;
import com.studygroup.util.creator.RoutingKeyGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequiredArgsConstructor
public class ChatMessageController {

  private final SendMessageService sendMessageService;

  @MessageMapping("/inquiry-room/{roomName}")
  public void sendMessageToInquiryChatRoom(@DestinationVariable String roomName,
      MessageDto message) {

    sendMessageService.sendMessage(RoutingKeyGenerator.inquiryRoutingKey(roomName), message);

  }

  @MessageMapping("/group-chat/{groupName}")
  public void sendMessageToGroupChatRoom(@PathVariable String groupName,
      @RequestBody MessageDto message) {
    sendMessageService.sendMessage(RoutingKeyGenerator.groupChatRoomRoutingKey(groupName), message);

  }


  @MessageMapping("/group-live-chat/{groupName}")
  public void sendMessageToLiveGroupChatRoom(@PathVariable String groupName,
      @RequestBody MessageDto message) {
    sendMessageService.sendMessage(RoutingKeyGenerator.liveGroupChatRoutingKey(groupName), message);
  }
}
