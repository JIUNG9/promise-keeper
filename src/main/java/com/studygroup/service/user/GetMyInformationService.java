package com.studygroup.service.user;

import com.studygroup.dto.MyInfoDto;
import com.studygroup.entity.Member;
import org.springframework.stereotype.Service;

@Service
public interface GetMyInformationService {
    MyInfoDto getMyInfo(Member member
    );
}
