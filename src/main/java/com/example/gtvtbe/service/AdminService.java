package com.example.gtvtbe.service;

import com.example.gtvtbe.common.Result;
import com.example.gtvtbe.exception.DataNotFoundException;
import com.example.gtvtbe.model.response.AccountResponse;
import com.example.gtvtbe.model.response.RoleResponse;

import java.util.List;

public interface AdminService {
    Result<List<RoleResponse>> getAuthorities();

    Result<AccountResponse> updateActiveAccount(String idAccount,String idRole) throws DataNotFoundException;
}
