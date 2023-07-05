package com.studygroup.service.chatroom;

import com.studygroup.dto.ChatRoomDto;
import com.studygroup.domain.Member;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface RetrieveMyChatRoomListService {

  List<ChatRoomDto> get(Member member);
}
