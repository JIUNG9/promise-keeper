package com.studygroup.service.chatroom;

import com.studygroup.domain.ChatRoom;
import com.studygroup.enums.RoomType;
import com.studygroup.repository.ChatRoomRepository;
import com.studygroup.util.creator.ChatRoomNameGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("CreateInquiryChatRoomService")
@RequiredArgsConstructor
public class CreateInquiryChatRoomService implements CreateOneToOneChatRoomService {

  private final ChatRoomRepository chatRoomRepository;

  @Override
  public ChatRoom create(String requestUserName, String groupName) {

    ChatRoom createdChatRoom = ChatRoom.
        builder().
        chatList(null).
        roomType(RoomType.INQUIRE_CHAT).
        name(ChatRoomNameGenerator.getInquiryRoomName(requestUserName, groupName)).
        build();

    return chatRoomRepository.save(createdChatRoom);

  }

}
