package com.studygroup.service;

import com.studygroup.entity.ChatMessage;
import org.springframework.stereotype.Service;

@Service
public interface SendMessageService {
    public void
    sendMessage(String routingKey, ChatMessage chatMessage);
}
