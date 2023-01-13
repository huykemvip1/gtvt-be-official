package com.example.gtvtbe.service.impl;

import com.example.gtvtbe.common.Result;
import com.example.gtvtbe.enumeration.EnumRoles;
import com.example.gtvtbe.exception.DataAlreadyExistException;
import com.example.gtvtbe.exception.DataNotFoundException;
import com.example.gtvtbe.model.entity.UserEntity;
import com.example.gtvtbe.model.request.ForgotAccountRequest;
import com.example.gtvtbe.model.request.SignupRequest;
import com.example.gtvtbe.repository.AccountRepository;
import com.example.gtvtbe.repository.RolesRepository;
import com.example.gtvtbe.repository.UsersRepository;
import com.example.gtvtbe.security.domain.Account;
import com.example.gtvtbe.security.domain.Roles;
import com.example.gtvtbe.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final RolesRepository rolesRepository;
    private final MessageSource messageSource;

    private final UsersRepository usersRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, RolesRepository rolesRepository, MessageSource messageSource, UsersRepository usersRepository) {
        this.accountRepository = accountRepository;
        this.rolesRepository = rolesRepository;
        this.messageSource = messageSource;
        this.usersRepository = usersRepository;
    }

    @Override
    public Result<Boolean> signUp(SignupRequest request) throws DataAlreadyExistException {
        Optional<Account> checkExist = accountRepository.findByUsername(request.getUsername());

        if (checkExist.isPresent()){
            log.error(request.getUsername()+" account already !!!!");
            throw new DataAlreadyExistException(messageSource.getMessage("entity.already.exists", new Object[]{request.getUsername()}, null));
        }
        String idUsers = usersRepository.save(UserEntity.builder()
                        .id(UUID.randomUUID().toString())
                        .firstName(request.getFirstName())
                        .lastName(request.getLastName())
                        .phoneNumber(request.getPhoneNumber())
                        .build())
                .getId();
        Set<Roles> roles = Set.of(
                Roles.builder().id(EnumRoles.ROLE_USER.getId()).name(EnumRoles.ROLE_USER.getName()).build()
        );
        String id = accountRepository.save(Account.builder()
                        .id(UUID.randomUUID().toString())
                        .username(request.getUsername())
                        .password(request.getPassword())
                        .idUsers(idUsers)
                        .roles(roles)
                        .questionSecurity(request.getQuestionSecurity())
                        .answerSecurity(request.getAnswerSecurity())
                        .isActive(true)
                        .build()
                ).getId();
        return Result.ok(true);
    }

    @Override
    public Result<Boolean> activeAccount(String id) throws DataNotFoundException {
        Account account = accountRepository.findById(id)
                .orElseThrow(() ->  new DataNotFoundException(messageSource.getMessage("entity.not.found",new Object[]{id},null)));
                account.setActive(true);
                accountRepository.save(account);
        return Result.ok(true);
    }

    @Override
    public String forgotPassword(ForgotAccountRequest request) throws DataNotFoundException {
        Account account = accountRepository.findByUsernameAndQuestionSecurityAndAnswerSecurity(request.getUsername(),
                        request.getQuestionSecurity(),
                        request.getAnswerSecurity())
                .orElseThrow(() -> new DataNotFoundException(messageSource.getMessage("entity.not.found",new Object[]{request},null)));
        UserEntity user = usersRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new DataNotFoundException(messageSource.getMessage("entity.not.found",new Object[]{request},null)));
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        String passwordReset = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        account.setPassword(passwordReset);
        accountRepository.save(account);
        return passwordReset;
    }
}
