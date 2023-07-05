package com.studygroup.service.chatroom;

import com.studygroup.domain.ChatRoom;
import com.studygroup.domain.Member;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public interface FindInquiryChatRoomService {

  Optional<ChatRoom> find(String roomName, Member member);
}
