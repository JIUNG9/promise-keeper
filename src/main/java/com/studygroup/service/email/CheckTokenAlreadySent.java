package com.studygroup.service.email;

import com.studygroup.domain.Member;

public interface CheckTokenAlreadySent {

  void checkTokenSentIfSoDelete(Member member);
}
