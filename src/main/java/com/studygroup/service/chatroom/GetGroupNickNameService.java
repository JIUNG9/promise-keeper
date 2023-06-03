package com.studygroup.service.chatroom;

import com.studygroup.entity.Member;
import org.springframework.stereotype.Service;

@Service
public interface GetGroupNickNameService {
    public String getNickName(Member member, String groupName);
}
