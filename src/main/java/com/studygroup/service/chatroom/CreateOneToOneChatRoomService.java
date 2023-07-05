package com.studygroup.service.chatroom;

import com.studygroup.domain.ChatRoom;
import org.springframework.stereotype.Service;

@Service
public interface CreateOneToOneChatRoomService {

  ChatRoom create(String requestUserName, String groupName);

}
