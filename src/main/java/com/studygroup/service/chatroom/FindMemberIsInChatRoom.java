package com.studygroup.service.chatroom;

import com.studygroup.entity.ChatRoom;
import com.studygroup.entity.Member;
import com.studygroup.entity.RoomMember;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface FindMemberIsInChatRoom {
    public Optional<RoomMember> find(Member member, String chatRoomName);
}
