package com.studygroup.service.group.plan;

import com.studygroup.dto.UpdateMeetingDateForm;
import com.studygroup.domain.Member;
import org.springframework.stereotype.Service;

@Service
public interface UpdateGroupMemberPlanService {

  void update(UpdateMeetingDateForm updateMeetingDateForm, Member member, String groupName);
}
