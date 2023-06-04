package com.studygroup.service.chatroom;

import com.studygroup.entity.ChatRoom;
import com.studygroup.enums.RoomType;
import com.studygroup.repository.ChatRoomRepository;
import com.studygroup.repository.GroupMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("FindGroupChatRoomServiceImpl")
@RequiredArgsConstructor
public class FindGroupChatRoomServiceImpl implements FindGroupChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    @Override
    public Optional<ChatRoom> find(String groupName) {
        return chatRoomRepository.findByRoomTypeAndNameContains(RoomType.GROUP_CHAT,groupName);
    }
}
