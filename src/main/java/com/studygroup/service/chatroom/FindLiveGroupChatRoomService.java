package com.studygroup.service.chatroom;

import com.studygroup.domain.ChatRoom;
import com.studygroup.enums.RoomType;
import com.studygroup.repository.ChatRoomRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("FindLiveGroupChatRoomService")
@RequiredArgsConstructor
public class FindLiveGroupChatRoomService implements FindGroupChatRoomService {

  private final ChatRoomRepository chatRoomRepository;

  @Override
  public Optional<ChatRoom> find(String groupName) {
    return chatRoomRepository.findByRoomTypeAndNameContains(RoomType.LIVE_GROUP_CHAT, groupName);
  }
}
