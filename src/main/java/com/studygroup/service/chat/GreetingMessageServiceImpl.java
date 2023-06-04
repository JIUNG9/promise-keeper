package com.studygroup.service.chat;

import com.studygroup.entity.ChatMessage;
import com.studygroup.entity.ChatRoom;
import com.studygroup.util.constant.GreetingChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service

public class GreetingMessageServiceImpl implements GreetingMessageService {
    @Override
    public ChatMessage get(String name, ChatRoom chatRoom) {
        String payload = GreetingChatMessage.greetMessage(name);

        return ChatMessage.
                builder().
                chatRoom(chatRoom).
                payload(payload).
                sender("채팅 관리자").
                build();

    }
}
