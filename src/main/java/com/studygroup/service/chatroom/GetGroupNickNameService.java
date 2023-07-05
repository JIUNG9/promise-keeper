package com.studygroup.service.chatroom;

import com.studygroup.domain.Member;
import org.springframework.stereotype.Service;

@Service
public interface GetGroupNickNameService {

  String getNickName(Member member, String groupName);
}
