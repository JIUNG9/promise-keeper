package com.studygroup.service.chatroom;

import com.studygroup.entity.ChatRoom;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface FindGroupChatRoomService {
    public Optional<ChatRoom> find(String groupName);
}
