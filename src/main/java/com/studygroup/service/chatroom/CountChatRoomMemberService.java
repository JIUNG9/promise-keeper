package com.studygroup.service.chatroom;

import com.studygroup.domain.ChatRoom;
import org.springframework.stereotype.Service;

@Service
public interface CountChatRoomMemberService {

  int get(ChatRoom chatRoom);
}
