package com.studygroup.service.user;

import com.studygroup.entity.Member;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void signUp(Member member);
    Member login(String email, String password);
    boolean logout();
    Member ResetPasswordByToken(String TokenString,Long memberId);
    boolean extendLoginSessionTime();
    Member updatePassword();
    Member withdrawalMemberSelf(String email, String password);
    Member withdrawalUserFromAdmin(String email, String password);
    Member initializePasswordByEmail(String email);

}
