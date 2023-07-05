package com.studygroup.repository;

import com.studygroup.domain.ChatMessage;
import com.studygroup.domain.ChatRoom;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

  List<ChatMessage> findByChatRoom(ChatRoom chatRoom);
}
