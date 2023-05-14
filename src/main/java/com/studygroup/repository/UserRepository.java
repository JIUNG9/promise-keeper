package com.studygroup.repository;

import com.studygroup.entity.Member;
import com.studygroup.enums.TokenType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<Member, Long>{
    Member findByEmailAndPassword(@Param("email") String email, @Param("password") String password);
    Member findByEmail(@Param("email") String email);
    @Query("select m from Member m JOIN FETCH  m.emailTokenList e where e.confirmationToken = :confirmationToken AND e.tokenType = :tokenType")
    Member findMemberByEmailTokenListByConfirmationToken(@Param("confirmationToken") String confirmationToken,TokenType tokenType);
}
