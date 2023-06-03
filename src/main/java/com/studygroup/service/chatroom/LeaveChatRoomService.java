package com.studygroup.service.chatroom;

import com.studygroup.entity.ChatRoom;
import com.studygroup.entity.Member;
import org.springframework.stereotype.Service;

@Service
public interface LeaveChatRoomService {
    public void goOut(Member member, ChatRoom chatRoom);
}
