package com.studygroup.service.chatroom;

import com.studygroup.entity.ChatRoom;
import org.springframework.stereotype.Service;

@Service
public interface CountChatRoomMemberService {
    public int get(ChatRoom chatRoom);
}
