package com.studygroup.service.groupmember;

import com.studygroup.entity.Member;
import com.studygroup.entity.StudyGroup;
import org.springframework.stereotype.Service;

@Service
public interface ApplyTheGroupService {
     void apply(Member member, StudyGroup studyGroup, String nickName, String intro);
}
