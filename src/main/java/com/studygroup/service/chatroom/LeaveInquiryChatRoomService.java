package com.studygroup.service.chatroom;

import org.springframework.stereotype.Service;

@Service
public interface LeaveInquiryChatRoomService {
    public void goOut(String userName, String groupName);
}
