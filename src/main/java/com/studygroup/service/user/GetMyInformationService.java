package com.studygroup.service.user;

import com.studygroup.dto.member.UserInfoDto;
import com.studygroup.domain.Member;
import org.springframework.stereotype.Service;

@Service
public interface GetMyInformationService {

  UserInfoDto getMyInfo(Member member
  );
}
