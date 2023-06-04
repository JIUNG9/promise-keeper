package com.studygroup.service.chatroom;

import com.studygroup.entity.ChatRoom;
import org.springframework.stereotype.Service;

@Service
public interface CreateOneToOneChatRoomService {
    public ChatRoom create(String requestUserName, String groupName);

}
