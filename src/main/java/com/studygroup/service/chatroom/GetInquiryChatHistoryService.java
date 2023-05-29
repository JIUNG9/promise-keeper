package com.studygroup.service.chatroom;

import com.studygroup.dto.MessageLog;
import com.studygroup.entity.Member;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GetInquiryChatHistoryService {
    public List<MessageLog> get(String requestMemberName,String groupName);
}
