package com.studygroup.service.chat;

import com.studygroup.entity.ChatMessage;
import com.studygroup.entity.ChatRoom;
import com.studygroup.util.constant.GreetingChatMessage;
import org.springframework.stereotype.Service;

@Service
public class ExitMessageServiceImpl implements ExitMessageService {

    @Override
    public ChatMessage get(String name, ChatRoom chatRoom) {
        String payload = GreetingChatMessage.exitMessage(name);

        return ChatMessage.
                    builder().
                    chatRoom(chatRoom).
                    payload(payload).
                    sender("채팅 관리자").
                    build();
    }
}
