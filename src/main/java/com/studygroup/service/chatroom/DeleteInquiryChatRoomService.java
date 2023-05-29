package com.studygroup.service.chatroom;

import com.studygroup.entity.ChatRoom;
import com.studygroup.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("DeleteInquiryChatRoomService")
@RequiredArgsConstructor
public class DeleteInquiryChatRoomService implements DeleteChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    @Override
    public void delete(ChatRoom chatRoom) {
        chatRoomRepository.delete(chatRoom);
    }
}
