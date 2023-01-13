package com.example.gtvtbe.repository;

import com.example.gtvtbe.security.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,String> {
    Optional<Account> findByUsernameNot(String username);
    Optional<Account> findByUsername(String username);

    Optional<Account> findByUsernameAndQuestionSecurityAndAnswerSecurity(String username,
                                                                         String questionSecurity,
                                                                         String answerSecurity);
}
