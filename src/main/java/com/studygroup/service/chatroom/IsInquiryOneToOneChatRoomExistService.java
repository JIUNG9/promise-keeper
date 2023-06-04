package com.studygroup.service.chatroom;


import com.studygroup.repository.ChatRoomRepository;
import com.studygroup.util.ChatRoomNameGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class IsInquiryOneToOneChatRoomExistService implements IsOneToOneChatRoomExistService {
    private final ChatRoomRepository chatRoomRepository;
    @Override
    public boolean isExist(String inquiryChatRoom) {

        return chatRoomRepository.existsByName(inquiryChatRoom);
    }
}
