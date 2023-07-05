package com.studygroup.service.chatroom;

import com.studygroup.domain.ChatRoom;
import com.studygroup.enums.RoomType;
import com.studygroup.repository.ChatRoomRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("FindGroupChatRoomServiceImpl")
@RequiredArgsConstructor
public class FindGroupChatRoomServiceImpl implements FindGroupChatRoomService {

  private final ChatRoomRepository chatRoomRepository;

  @Override
  public Optional<ChatRoom> find(String groupName) {
    return chatRoomRepository.findByRoomTypeAndNameContains(RoomType.GROUP_CHAT, groupName);
  }
}
