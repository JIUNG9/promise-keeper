package com.studygroup.service.user;

import com.studygroup.dto.member.MemberSignUpForm;
import com.studygroup.exception.CustomIllegalArgumentException;
import com.studygroup.repository.UserRepository;
import com.studygroup.service.common.DuplicationCheckerService;
import com.studygroup.service.group.member.CheckEmailDuplicationService;
import com.studygroup.util.error.ErrorCode;
import org.springframework.stereotype.Service;


@Service
public class NormalSignUpService implements SignUpService {

  private final CreateUserService createUserService;
  private final DuplicationCheckerService<String> checkDuplicationService;

  public NormalSignUpService(CreateUserService createUserService,
      UserRepository userRepository) {
    this.createUserService = createUserService;
    this.checkDuplicationService = new DuplicationCheckerService<>(
        new CheckEmailDuplicationService(userRepository));
  }


  @Override
  public void signUp(MemberSignUpForm signUpForm) {
    if(checkDuplicationService.isDuplicated(signUpForm.getEmail())){
        throw new CustomIllegalArgumentException(ErrorCode.EMAIL_IS_DUPLICATED);
    }
    createUserService.create(signUpForm);
  }
}
