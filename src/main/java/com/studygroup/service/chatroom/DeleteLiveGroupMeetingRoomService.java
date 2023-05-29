package com.studygroup.service.chatroom;

import com.studygroup.entity.ChatRoom;
import com.studygroup.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("DeleteLiveGroupMeetingRoomService")
@RequiredArgsConstructor
public class DeleteLiveGroupMeetingRoomService implements DeleteChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    @Override
    public void delete(ChatRoom chatRoom) {
        chatRoomRepository.delete(chatRoom);
    }
}
