package com.studygroup.service.chatroom;

import com.studygroup.domain.ChatRoom;
import com.studygroup.domain.Member;
import com.studygroup.domain.RoomMember;
import com.studygroup.repository.ChatRoomMemberRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetChatHistoryServiceImpl implements GetChatHistoryService {

  private final ChatRoomMemberRepository chatRoomMemberRepository;

  @Override
  public List<MessageLog> get(Member member, ChatRoom chatRoom) {

    RoomMember roomMember = chatRoomMemberRepository.
        findByMemberAndRoom(member, chatRoom).get();
    ChatRoom theRoomMemberIs = roomMember.getRoom();

    return theRoomMemberIs.
        getChatList().
        stream().
        filter(createdTime ->
            createdTime.
                getCreatedDate().
                compareTo(roomMember.getCreatedDate()) == 1).
        filter(adminMessage -> !adminMessage.getSender().equals("채팅 관리자")).
        map(chatList ->
            MessageLog.
                builder().
                payload(chatList.getPayload()).
                sender(chatList.getSender()).
                build()).
        collect(Collectors.toList());


  }
}
