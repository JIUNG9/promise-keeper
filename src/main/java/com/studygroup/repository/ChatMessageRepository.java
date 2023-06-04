package com.studygroup.repository;

import com.studygroup.entity.ChatMessage;
import com.studygroup.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage,Long> {

    public List<ChatMessage> findByChatRoom(ChatRoom chatRoom);
}
