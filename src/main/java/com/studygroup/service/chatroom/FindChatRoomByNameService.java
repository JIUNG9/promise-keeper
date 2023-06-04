package com.studygroup.service.chatroom;

import com.studygroup.entity.ChatRoom;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface FindChatRoomByNameService {
    public Optional<ChatRoom> find(String roomName);
}
