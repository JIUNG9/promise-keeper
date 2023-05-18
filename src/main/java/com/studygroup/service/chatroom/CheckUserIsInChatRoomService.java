package com.studygroup.service.chatroom;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface CheckUserIsInChatRoomService {
    boolean check(Long memberID, UUID roomId);
}
