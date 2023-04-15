package com.studygroup.repository;

import com.studygroup.entity.EmailToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailRepository extends JpaRepository<EmailToken, Integer> {
    void deleteById(Long id);
    List<EmailToken> findByMember_Id(Long memberId);
}
