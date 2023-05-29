package com.studygroup.service.chatroom;

import com.studygroup.entity.ChatRoom;
import com.studygroup.repository.ChatRoomMemberRepository;
import com.studygroup.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CountChatRoomMemberImpl implements CountChatRoomMember {
    private final ChatRoomMemberRepository chatRoomMemberRepository;
    @Override
    public int get(ChatRoom chatRoom) {
        return chatRoomMemberRepository.countByRoom(chatRoom);
    }
}
