package com.studygroup.service.chatroom;

import org.springframework.stereotype.Service;

@Service
public interface IsOneToOneChatRoomExistService {

  boolean isExist(String roomName);
}
