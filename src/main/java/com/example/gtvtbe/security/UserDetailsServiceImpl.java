package com.example.gtvtbe.security;

import com.example.gtvtbe.repository.AccountRepository;
import com.example.gtvtbe.security.domain.Account;
import com.example.gtvtbe.security.domain.AccountInformation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AccountRepository accountRepository;

    public UserDetailsServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> account = accountRepository.findByUsername(username);
        if (account.isPresent()){
            return new AccountInformation(account.get());
        }
        log.error("Username " + username + " not found !!!");
        throw new UsernameNotFoundException("Username : " + username + " not found !!!");
    }
}
