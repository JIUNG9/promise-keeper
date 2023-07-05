package com.studygroup.service.chatroom;

import com.studygroup.domain.ChatRoom;
import com.studygroup.repository.ChatRoomMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CountChatRoomMemberServiceImpl implements CountChatRoomMemberService {

  private final ChatRoomMemberRepository chatRoomMemberRepository;

  @Override
  public int get(ChatRoom chatRoom) {
    return chatRoomMemberRepository.countByRoom(chatRoom);
  }
}
