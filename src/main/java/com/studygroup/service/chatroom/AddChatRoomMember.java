package com.studygroup.service.chatroom;

import com.studygroup.domain.ChatRoom;
import com.studygroup.domain.Member;
import org.springframework.stereotype.Service;

@Service
public interface AddChatRoomMember {

  void addMember(ChatRoom chatRoom, Member member);
}
