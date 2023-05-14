package com.studygroup.service.email;

import com.studygroup.entity.EmailToken;
import com.studygroup.entity.Member;
import com.studygroup.enums.TokenType;
import com.studygroup.repository.EmailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
