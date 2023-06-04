package com.studygroup.service.chatroom;

import org.springframework.stereotype.Service;

@Service
public interface IsOneToOneChatRoomExistService {
    public boolean isExist(String roomName);
}
