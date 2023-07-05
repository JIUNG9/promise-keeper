package com.studygroup.repository;

import com.studygroup.domain.EmailToken;
import com.studygroup.domain.Member;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface EmailRepository extends JpaRepository<EmailToken, Long> {

  Optional<EmailToken> findByValue(String value);
  List<EmailToken> findByMember(Member member);


}
