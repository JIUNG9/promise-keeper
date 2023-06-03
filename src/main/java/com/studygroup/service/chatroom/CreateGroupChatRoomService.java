package com.studygroup.service.chatroom;

import com.studygroup.entity.ChatRoom;
import com.studygroup.enums.RoomType;
import com.studygroup.repository.ChatRoomRepository;
import com.studygroup.util.ChatRoomNameGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("CreateGroupChatRoomService")
@RequiredArgsConstructor
public class CreateGroupChatRoomService implements CreateChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    @Override
    public ChatRoom create(String groupName) {
        
        ChatRoom chatRoom = ChatRoom.
                                builder().
                                name(ChatRoomNameGenerator.getGroupChatRoomName(groupName)).
                                chatList(null).
                                roomType(RoomType.GROUP_CHAT).
                                build();

        return chatRoomRepository.save(chatRoom);

    }
}
