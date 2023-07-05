package com.studygroup.service.chatroom;

import com.studygroup.domain.ChatRoom;
import com.studygroup.domain.Member;
import com.studygroup.domain.RoomMember;
import com.studygroup.repository.ChatRoomMemberRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindInquiryChatRoomServiceImpl implements FindInquiryChatRoomService {

  private final ChatRoomMemberRepository chatRoomMemberRepository;

  @Override
  public Optional<ChatRoom> find(String inquiryRoomName, Member member) {

    Optional<RoomMember> roomMember =
        chatRoomMemberRepository.
            findByMemberAndRoom_Name(member, inquiryRoomName);

    if (roomMember.isPresent()) {
      return Optional.ofNullable(roomMember.get().getRoom());
    }

    return Optional.empty();


  }
}
