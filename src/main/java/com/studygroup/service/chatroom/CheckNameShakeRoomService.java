package com.studygroup.service.chatroom;

import com.studygroup.entity.ChatRoom;
import com.studygroup.entity.Member;
import org.springframework.stereotype.Service;

@Service
public interface CheckNameShakeRoomService {
    boolean isThereMember(ChatRoom chatRoom , Member member);
}
