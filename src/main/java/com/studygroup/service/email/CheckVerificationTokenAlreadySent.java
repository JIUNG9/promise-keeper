package com.studygroup.service.email;

import com.studygroup.domain.EmailToken;
import com.studygroup.domain.Member;
import com.studygroup.enums.TokenType;
import com.studygroup.repository.EmailRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service("CheckVerificationTokenAlreadySent")
public class CheckVerificationTokenAlreadySent implements CheckTokenAlreadySent {

  private final EmailRepository emailRepo;

  @Override
  public void checkTokenSentIfSoDelete(Member member) {

    List<EmailToken> emailTokenList = emailRepo.findByMember(member);
    List<Long> passwordResetTokens = emailTokenList.
        stream().
        filter(s -> s.getTokenType().equals(TokenType.VERIFICATION_TOKEN)).
        map(s -> s.getId()).collect(Collectors.toList());

    if (passwordResetTokens.size() != 0) {
      for (Long id : passwordResetTokens) {
        emailRepo.deleteById(id);
      }
    }
  }
}
