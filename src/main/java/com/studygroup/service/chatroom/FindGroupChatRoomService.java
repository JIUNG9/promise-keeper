package com.studygroup.service.chatroom;

import com.studygroup.domain.ChatRoom;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public interface FindGroupChatRoomService {

  Optional<ChatRoom> find(String groupName);
}
