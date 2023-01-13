package com.example.gtvtbe.service.impl;

import com.example.gtvtbe.common.Result;
import com.example.gtvtbe.exception.DataNotFoundException;
import com.example.gtvtbe.model.response.AccountResponse;
import com.example.gtvtbe.model.response.RoleResponse;
import com.example.gtvtbe.repository.AccountRepository;
import com.example.gtvtbe.repository.RolesRepository;
import com.example.gtvtbe.security.domain.Account;
import com.example.gtvtbe.security.domain.Roles;
import com.example.gtvtbe.service.AdminService;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    private final RolesRepository rolesRepository;

    private final AccountRepository accountRepository;

    private final MessageSource messageSource;

    public AdminServiceImpl(RolesRepository rolesRepository, AccountRepository accountRepository, MessageSource messageSource) {
        this.rolesRepository = rolesRepository;
        this.accountRepository = accountRepository;
        this.messageSource = messageSource;
    }

    @Override
    public Result<List<RoleResponse>> getAuthorities() {
        var response = rolesRepository.findAll()
                .stream().map(this::getAuthorities)
                .collect(Collectors.toList());
        return Result.ok(response);
    }

    @Override
    public Result<AccountResponse> updateActiveAccount(String idAccount, String idRole) throws DataNotFoundException {
        var account = accountRepository.findById(idAccount)
                .orElseThrow(() -> new DataNotFoundException(messageSource.getMessage("entity.not.found",new Object[]{idAccount},null)));
        var role = rolesRepository.findById(idRole)
                .orElseThrow(() -> new DataNotFoundException(messageSource.getMessage("entity.not.found",new Object[]{idRole},null)));
        Set<Roles> roles = new HashSet<>();
        roles.add(role);
        account.setActive(true);
        account.setRoles(roles);
        var response = getAccountResponse(accountRepository.save(account));
        return Result.ok(response);
    }

    private RoleResponse getAuthorities(Roles entity) {
        return new RoleResponse(entity.getId(), entity.getName());
    }
    private AccountResponse getAccountResponse(Account entity) {
        return new AccountResponse(entity.getUsername(),
                entity.getPassword(),
                entity.isActive(),
                entity.getRoles().stream().map(this::getAuthorities).collect(Collectors.toList()));
    }
}
