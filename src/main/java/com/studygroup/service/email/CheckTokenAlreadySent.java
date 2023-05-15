package com.studygroup.service.email;

import com.studygroup.entity.Member;

public interface CheckTokenAlreadySent {
    void checkTokenSentIfSoDelete(Member member);
}
