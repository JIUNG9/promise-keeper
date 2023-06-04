package com.studygroup.service.chat;

import com.studygroup.entity.ChatMessage;
import com.studygroup.entity.ChatRoom;
import org.springframework.stereotype.Service;

@Service
public interface GreetingMessageService {
   public ChatMessage get(String name, ChatRoom chatRoom);
}
