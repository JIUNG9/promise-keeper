package com.studygroup.service.chatroom;

import com.studygroup.entity.ChatRoom;
import com.studygroup.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public interface CreateChatRoomService {
    public ChatRoom create(String groupName);
}
