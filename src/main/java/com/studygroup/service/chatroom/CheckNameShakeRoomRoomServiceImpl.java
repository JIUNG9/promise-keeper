package com.studygroup.service.chatroom;

import com.studygroup.domain.ChatRoom;
import com.studygroup.domain.Member;
import com.studygroup.repository.ChatRoomMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckNameShakeRoomRoomServiceImpl implements CheckNameShakeRoomService {

  private final ChatRoomMemberRepository chatRoomMemberRepository;

  @Override
  public boolean isThereMember(ChatRoom chatRoom, Member member) {

    return chatRoomMemberRepository.findByMemberAndRoom(member, chatRoom).isPresent();
  }
}
