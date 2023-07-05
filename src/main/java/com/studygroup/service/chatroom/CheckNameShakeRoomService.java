package com.studygroup.service.chatroom;

import com.studygroup.domain.ChatRoom;
import com.studygroup.domain.Member;
import org.springframework.stereotype.Service;

@Service
public interface CheckNameShakeRoomService {

  boolean isThereMember(ChatRoom chatRoom, Member member);
}
