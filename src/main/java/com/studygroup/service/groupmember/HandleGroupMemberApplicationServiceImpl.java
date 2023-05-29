package com.studygroup.service.groupmember;

import com.studygroup.entity.StudyGroupMember;
import com.studygroup.enums.GroupMemberApplicationConSentOrDeny;
import com.studygroup.enums.GroupRole;
import com.studygroup.repository.GroupMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HandleGroupMemberApplicationServiceImpl implements HandleGroupMemberApplicationService {

    private final GroupMemberRepository groupMemberRepository;

    @Override
    public void handle(StudyGroupMember studyGroupMember, GroupMemberApplicationConSentOrDeny groupMemberApplicationConSentOrDeny) {

        if (groupMemberApplicationConSentOrDeny.equals(GroupMemberApplicationConSentOrDeny.CONSENT)) {
            StudyGroupMember consentTheMember = StudyGroupMember.
                    builder().
                    studyGroup(studyGroupMember.getStudyGroup()).
                    member(studyGroupMember.getMember()).
                    warnCount(studyGroupMember.getWarnCount()).
                    nickName(studyGroupMember.getNickName()).
                    intro(studyGroupMember.getIntro()).
                    id(studyGroupMember.getId()).
                    studyGroupMemberPlanList(studyGroupMember.getStudyGroupMemberPlanList()).
                    groupRole(GroupRole.GROUP_USER).build();

            groupMemberRepository.save(consentTheMember);
        }
        else{
            StudyGroupMember denyTheMember = StudyGroupMember.
                    builder().
                    studyGroup(studyGroupMember.getStudyGroup()).
                    member(studyGroupMember.getMember()).
                    warnCount(studyGroupMember.getWarnCount()).
                    nickName(studyGroupMember.getNickName()).
                    intro(studyGroupMember.getIntro()).
                    studyGroupMemberPlanList(studyGroupMember.getStudyGroupMemberPlanList()).
                    id(studyGroupMember.getId()).
                    groupRole(GroupRole.GROUP_DENIED).build();

            groupMemberRepository.save(denyTheMember);
        }
    }

}