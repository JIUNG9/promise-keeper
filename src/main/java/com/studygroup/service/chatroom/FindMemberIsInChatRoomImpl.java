package com.studygroup.service.chatroom;

import com.studygroup.domain.Member;
import com.studygroup.domain.RoomMember;
import com.studygroup.repository.ChatRoomMemberRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindMemberIsInChatRoomImpl implements FindMemberIsInChatRoom {

  private final ChatRoomMemberRepository chatRoomMemberRepository;

  @Override
  public Optional<RoomMember> find(Member member, String chatRoomName) {

    return chatRoomMemberRepository.
        findByMemberAndRoom_Name(member, chatRoomName);
  }

  @Override
  public Optional<RoomMember> find(Long roomMemberId) {
    return chatRoomMemberRepository.findById(roomMemberId);
  }
}
