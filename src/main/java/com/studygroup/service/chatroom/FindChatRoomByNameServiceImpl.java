package com.studygroup.service.chatroom;

import com.studygroup.entity.ChatRoom;
import com.studygroup.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindChatRoomByNameServiceImpl implements FindChatRoomByNameService {
    private final ChatRoomRepository chatRoomRepository;
    @Override
    public Optional<ChatRoom> find(String roomName) {
        return chatRoomRepository.findByName(roomName);
    }
}
