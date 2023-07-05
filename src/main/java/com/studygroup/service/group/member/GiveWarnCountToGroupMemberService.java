package com.studygroup.service.group.member;

import org.springframework.stereotype.Service;

@Service
public interface GiveWarnCountToGroupMemberService {

  void giveCountBy1(String groupName, String nickName);
}
