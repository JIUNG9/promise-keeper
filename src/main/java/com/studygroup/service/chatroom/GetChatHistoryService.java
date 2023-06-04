package com.studygroup.service.chatroom;

import com.studygroup.dto.MessageLog;
import com.studygroup.entity.ChatRoom;
import com.studygroup.entity.Member;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GetChatHistoryService {
    public List<MessageLog> get(Member member, ChatRoom chatRoom);
}
