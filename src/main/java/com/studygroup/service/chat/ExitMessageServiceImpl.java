package com.studygroup.service.chat;

import com.studygroup.domain.ChatMessage;
import com.studygroup.domain.ChatRoom;
import com.studygroup.util.constant.GreetingChatMessage;
import org.springframework.stereotype.Service;

@Service
public class ExitMessageServiceImpl implements ExitMessageService {

  @Override
  public ChatMessage get(String name, ChatRoom chatRoom) {
    String payload = GreetingChatMessage.exitMessage(name);

    return ChatMessage.
        builder().
        sender("채팅관리자").
        payload(payload).
        chatRoom(chatRoom).
        build();
  }
}
