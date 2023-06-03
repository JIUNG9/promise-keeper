package com.studygroup.service.chatroom;

import com.studygroup.entity.ChatRoom;
import com.studygroup.entity.Member;
import com.studygroup.entity.RoomMember;
import com.studygroup.enums.RoomType;
import com.studygroup.repository.ChatRoomMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddChatRoomMemberImpl implements AddChatRoomMember {
    private final ChatRoomMemberRepository chatRoomMemberRepository;
    @Override
    public void addMember(ChatRoom chatRoom, Member member) {

        RoomMember newMember = RoomMember.
                                    builder().
                                    member(member).
                                    room(chatRoom).
                                    build();

        chatRoomMemberRepository.save(newMember);
    }
}
