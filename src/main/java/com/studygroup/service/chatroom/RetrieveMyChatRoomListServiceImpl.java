package com.studygroup.service.chatroom;

import com.studygroup.dto.MyChatRoomDto;
import com.studygroup.entity.Member;
import com.studygroup.entity.RoomMember;
import com.studygroup.repository.ChatRoomMemberRepository;
import com.studygroup.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("RetrieveMyChatRoomListServiceImpl")
@RequiredArgsConstructor
public class RetrieveMyChatRoomListServiceImpl implements RetrieveMyChatRoomListService {
    private final ChatRoomMemberRepository chatRoomMemberRepository;
    @Override
    public List<MyChatRoomDto> get(Member member) {

        List<RoomMember> roomMemberList = chatRoomMemberRepository.findByMember(member);
        return roomMemberList.
                    stream().
                    map(roomMember->
                        MyChatRoomDto.
                                builder().
                                roomType(roomMember.getRoom().getRoomType()).
                                roomName(roomMember.getRoom().getName()).
                                build()).
                collect(Collectors.toList());

    }
}
