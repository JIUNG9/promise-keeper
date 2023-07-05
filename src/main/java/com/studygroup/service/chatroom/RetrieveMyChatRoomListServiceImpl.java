package com.studygroup.service.chatroom;

import com.studygroup.dto.ChatRoomDto;
import com.studygroup.domain.Member;
import com.studygroup.domain.RoomMember;
import com.studygroup.repository.ChatRoomMemberRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("RetrieveMyChatRoomListServiceImpl")
@RequiredArgsConstructor
public class RetrieveMyChatRoomListServiceImpl implements RetrieveMyChatRoomListService {

  private final ChatRoomMemberRepository chatRoomMemberRepository;

  @Override
  public List<ChatRoomDto> get(Member member) {

    List<RoomMember> roomMemberList = chatRoomMemberRepository.findByMember(member);
    return roomMemberList.
        stream().
        map(roomMember ->
            ChatRoomDto.
                builder().
                roomType(roomMember.getRoom().getRoomType()).
                roomName(roomMember.getRoom().getName()).
                build()).
        collect(Collectors.toList());

  }
}
