package com.studygroup.service.chatroom;

import com.studygroup.domain.ChatRoom;
import com.studygroup.domain.Member;
import com.studygroup.domain.RoomMember;
import com.studygroup.repository.ChatRoomMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddChatRoomMemberImpl implements AddChatRoomMember {

  private final ChatRoomMemberRepository chatRoomMemberRepository;

  @Override
  public void addMember(ChatRoom chatRoom, Member member) {

    RoomMember newMember = RoomMember.
        builder().
        member(member).
        room(chatRoom).
        build();

    chatRoomMemberRepository.save(newMember);
  }
}
