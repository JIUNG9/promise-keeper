package com.studygroup.service.group.member;

import com.studygroup.repository.GroupMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HandOverAdminService implements SetGroupAdminService {

  private final GroupMemberRepository groupMemberRepository;

  @Override
  public void setAdmin(String groupName, String userNickName) {

  }
}
