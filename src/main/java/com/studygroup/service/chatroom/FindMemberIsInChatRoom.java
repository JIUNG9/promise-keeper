package com.studygroup.service.chatroom;

import com.studygroup.domain.Member;
import com.studygroup.domain.RoomMember;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public interface FindMemberIsInChatRoom {

  Optional<RoomMember> find(Member member, String chatRoomName);

  Optional<RoomMember> find(Long roomMemberId);
}
