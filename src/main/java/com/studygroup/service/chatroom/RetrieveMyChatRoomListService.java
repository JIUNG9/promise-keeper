package com.studygroup.service.chatroom;

import com.studygroup.dto.MyChatRoomDto;
import com.studygroup.entity.Member;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RetrieveMyChatRoomListService {
    public List<MyChatRoomDto> get(Member member);
}
