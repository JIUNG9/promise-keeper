package com.studygroup.service.chatroom;

import com.studygroup.entity.*;
import com.studygroup.enums.RoomType;
import com.studygroup.repository.*;
import com.studygroup.util.ChatRoomNameGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindOneToOneChatRoomService implements FindInquiryChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    @Override
    public Optional<ChatRoom> find(String groupName, String requestMemberName) {

        String inquiryRoomName = ChatRoomNameGenerator.getInquiryRoomName(requestMemberName,groupName);

        return chatRoomRepository.findByNameAndRoomType(inquiryRoomName,RoomType.INQUIRE_CHAT);





    }
}
