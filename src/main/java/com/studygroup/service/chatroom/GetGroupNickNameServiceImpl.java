package com.studygroup.service.chatroom;

import com.studygroup.entity.Member;
import com.studygroup.repository.GroupMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetGroupNickNameServiceImpl implements GetGroupNickNameService {
    private final GroupMemberRepository groupMemberRepository;
    @Override
    public String getNickName(Member member, String groupName) {
        return groupMemberRepository.
                findByStudyGroup_NameAndMember(groupName,member).
                getNickName();
    }
}
