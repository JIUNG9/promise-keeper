package com.studygroup.service.chatroom;

import com.studygroup.domain.ChatRoom;
import com.studygroup.repository.ChatRoomRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindChatRoomByNameServiceImpl implements FindChatRoomByNameService {

  private final ChatRoomRepository chatRoomRepository;

  @Override
  public Optional<ChatRoom> find(String roomName) {
    return chatRoomRepository.findByName(roomName);
  }
}
