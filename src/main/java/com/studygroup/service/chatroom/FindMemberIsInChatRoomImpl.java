package com.studygroup.service.chatroom;

import com.studygroup.entity.ChatRoom;
import com.studygroup.entity.Member;
import com.studygroup.entity.RoomMember;
import com.studygroup.repository.ChatRoomMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindMemberIsInChatRoomImpl implements FindMemberIsInChatRoom {
    private final ChatRoomMemberRepository chatRoomMemberRepository;
    @Override
    public Optional<RoomMember> find(Member member, String chatRoomName) {
        
        return chatRoomMemberRepository.
                findByMemberAndRoom_Name(member, chatRoomName);
    }
}
