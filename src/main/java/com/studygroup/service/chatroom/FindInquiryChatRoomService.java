package com.studygroup.service.chatroom;

import com.studygroup.entity.ChatRoom;
import com.studygroup.entity.Member;
import com.studygroup.entity.StudyGroup;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface FindInquiryChatRoomService {
    public Optional<ChatRoom> find(String roomName, Member member);
}
