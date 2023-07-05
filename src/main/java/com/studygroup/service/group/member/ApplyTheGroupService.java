package com.studygroup.service.group.member;

import com.studygroup.domain.Member;
import com.studygroup.domain.StudyGroup;
import org.springframework.stereotype.Service;

@Service
public interface ApplyTheGroupService {

  void apply(Member member, StudyGroup studyGroup, String nickName, String intro);
}
