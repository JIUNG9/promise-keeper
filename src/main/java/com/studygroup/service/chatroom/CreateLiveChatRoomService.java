package com.studygroup.service.chatroom;

import com.studygroup.entity.ChatRoom;
import com.studygroup.enums.RoomType;
import com.studygroup.repository.ChatRoomRepository;
import com.studygroup.util.ChatRoomNameGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("CreateLiveChatRoomService")
@RequiredArgsConstructor
public class CreateLiveChatRoomService implements CreateChatRoomService {
    private final ChatRoomRepository chatRoomRepository;

    @Override
    public ChatRoom create(String groupName) {

        ChatRoom chatRoom = ChatRoom.
                builder().
                name(ChatRoomNameGenerator.getLiveChatRoomName(groupName)).
                chatList(null).
                roomType(RoomType.LIVE_GROUP_CHAT).
                build();

        return chatRoomRepository.save(chatRoom);

    }

}
