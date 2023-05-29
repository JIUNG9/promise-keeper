package com.studygroup.service.chatroom;

import com.studygroup.dto.MessageLog;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GetGroupChatHistoryService {
    public List<MessageLog> get(String groupName);
}
