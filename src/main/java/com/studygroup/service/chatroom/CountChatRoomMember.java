package com.studygroup.service.chatroom;

import com.studygroup.entity.ChatRoom;
import org.springframework.stereotype.Service;

@Service
public interface CountChatRoomMember {
    public int get(ChatRoom chatRoom);
}
