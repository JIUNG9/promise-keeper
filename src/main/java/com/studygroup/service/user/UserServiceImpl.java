package com.studygroup.service.user;

import com.studygroup.entity.EmailToken;
import com.studygroup.entity.Member;
import com.studygroup.repository.EmailRepository;
import com.studygroup.repository.UserRepository;
import com.studygroup.util.constant.ErrorCode;
import com.studygroup.util.constant.TokenGenerator;
import com.studygroup.util.lambda.BindParameterSupplier;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final EmailRepository emailRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;



    @Override
    public void signUp(Member member) {

        //Email duplication check
        String memberEmail = member.getEmail();

        if(!Optional.of(userRepository.findByEmail(memberEmail)).isPresent()){

            Member insertMember = Member.
                    builder().
                    email(member.getEmail()).
                    name(member.getName()).
                    email_verified(false).
                    password(bCryptPasswordEncoder.encode(member.getPassword())).
                    build();

            EmailToken emailToken = EmailToken.
                    builder().
                    member(insertMember).
                    confirmationToken(TokenGenerator.setTokenString())
                    .build();

            emailRepository.save(emailToken);

        }

        else{
            throw new IllegalArgumentException(ErrorCode.EMAIL_IS_DUPLICATED.getMessage());
        }


    }

    @Override
    public Member login(String email, String password) {

        return Optional.
                        of(userRepository.findByEmailAndPassword(
                            email, bCryptPasswordEncoder.encode(password))).
                                 orElseThrow(
                                    BindParameterSupplier.bind(
                                            BadCredentialsException::new, ErrorCode.LOGIN_FAIL.getMessage()));
    }

    @Override
    public boolean logout() {
        return false;
    }

    @Override
    public Member ResetPasswordByToken(String TokenString, Long memberId) {

        return Optional.
                        of(userRepository.findById(memberId)).
                            orElseThrow(
                                BindParameterSupplier.bind(
                                    EntityNotFoundException::new, ErrorCode.THERE_IS_NO_EMAIL.getMessage()));
    }

    @Override
    public boolean extendLoginSessionTime() {
        return false;
    }

    @Override
    public Member updatePassword() {
        return null;
    }

    @Override
    public Member withdrawalMemberSelf(String email, String password) {

        return Optional.
                        of(userRepository.findByEmailAndPassword(
                                email, bCryptPasswordEncoder.encode(password))).
                                    orElseThrow(BindParameterSupplier.bind(
                                            EntityNotFoundException::new, ErrorCode.CAN_NOT_WITHDRAWAL.getMessage()));
    }

    @Override
    public Member withdrawalUserFromAdmin(String email, String password) {
        return null;
    }

    @Override
    public Member initializePasswordByEmail(String email) {

        return Optional.
                        of(userRepository.findByEmail(email)).
                            orElseThrow(BindParameterSupplier.bind(
                                    EntityNotFoundException::new ,ErrorCode.THERE_IS_NO_EMAIL.getMessage()));

    }

    @Override
    public Member loadUserByUsername(String email) throws UsernameNotFoundException {

        return Optional.
                        of(userRepository.findByEmail(email)).
                            orElseThrow(BindParameterSupplier.bind(
                                    UsernameNotFoundException::new,ErrorCode.THERE_IS_NO_EMAIL.getMessage()));

    }
}