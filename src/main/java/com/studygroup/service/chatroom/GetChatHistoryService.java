package com.studygroup.service.chatroom;

import com.studygroup.dto.MessageDto;
import com.studygroup.domain.ChatRoom;
import com.studygroup.domain.Member;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface GetChatHistoryService {

  List<MessageDto> get(Member member, ChatRoom chatRoom);
}
