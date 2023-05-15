package com.studygroup.repository;

import com.studygroup.entity.EmailToken;
import com.studygroup.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EmailRepository extends JpaRepository<EmailToken, Integer> {


    @Transactional
    void deleteById(Long id);
    List<EmailToken> findByMember(Member member);


}
