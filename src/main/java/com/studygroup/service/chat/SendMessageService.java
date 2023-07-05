package com.studygroup.service.chat;

import com.studygroup.domain.ChatMessage;
import org.springframework.stereotype.Service;

@Service
public interface SendMessageService {

  void sendMessage(String routingKey, ChatMessage chatMessage);
}
