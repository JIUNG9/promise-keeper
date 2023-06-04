package com.studygroup.service.chatroom;

import com.studygroup.entity.ChatRoom;
import com.studygroup.enums.RoomType;
import com.studygroup.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("FindLiveGroupChatRoomService")
@RequiredArgsConstructor
public class FindLiveGroupChatRoomService implements FindGroupChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    @Override
    public Optional<ChatRoom> find(String groupName) {
        return chatRoomRepository.findByRoomTypeAndNameContains(RoomType.LIVE_GROUP_CHAT, groupName);
    }
}
