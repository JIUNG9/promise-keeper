package com.studygroup.service.email;

import com.studygroup.domain.EmailToken;
import com.studygroup.domain.Member;
import com.studygroup.enums.TokenType;
import com.studygroup.repository.EmailRepository;
import com.studygroup.util.token.TokenGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("SaveTheVerificationToken")
@RequiredArgsConstructor
public class SaveTheVerificationToken implements SaveTheToken {

  private final EmailRepository emailRepo;


  @Override
  public String save(Member member) {

    String token = TokenGenerator.setTokenString();

    EmailToken emailToken = EmailToken.
        builder().
        member(member).
        tokenType(TokenType.VERIFICATION_TOKEN).
        confirmationToken(token).
        build();

    emailRepo.save(emailToken);

    return token;
  }
}
