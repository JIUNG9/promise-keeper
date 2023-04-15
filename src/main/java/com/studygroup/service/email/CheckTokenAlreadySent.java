package com.studygroup.service.email;

import com.studygroup.entity.EmailToken;

public interface CheckTokenAlreadySent {
    void checkTokenSentIfSoDelete(Long memberId);
}
