package com.studygroup.service.groupmember;

import com.studygroup.entity.Member;
import com.studygroup.enums.RoomType;
import org.springframework.stereotype.Service;

@Service
public interface LeaveGroupChatRoomService {
    public void goOut(String groupName, Member member);
}
