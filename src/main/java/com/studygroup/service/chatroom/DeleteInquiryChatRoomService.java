package com.studygroup.service.chatroom;

import com.studygroup.domain.ChatRoom;
import com.studygroup.repository.ChatMessageRepository;
import com.studygroup.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("DeleteInquiryChatRoomService")
@RequiredArgsConstructor
public class DeleteInquiryChatRoomService implements DeleteChatRoomService {

  private final ChatRoomRepository chatRoomRepository;
  private final ChatMessageRepository chatMessageRepository;

  @Override
  @Transactional
  public void delete(ChatRoom chatRoom) {
    chatMessageRepository.deleteAll(chatRoom.getChatList());
    chatRoomRepository.delete(chatRoom);
  }
}
