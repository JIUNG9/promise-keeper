package com.studygroup.service.chatroom;

import com.studygroup.domain.ChatRoom;
import org.springframework.stereotype.Service;

@Service
public interface CreateChatRoomService {

  ChatRoom create(String groupName);
}
