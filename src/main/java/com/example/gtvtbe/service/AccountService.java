package com.example.gtvtbe.service;

import com.example.gtvtbe.common.Result;
import com.example.gtvtbe.exception.DataAlreadyExistException;
import com.example.gtvtbe.exception.DataNotFoundException;
import com.example.gtvtbe.model.request.ForgotAccountRequest;
import com.example.gtvtbe.model.request.SignupRequest;

public interface AccountService {
    Result<Boolean> signUp(SignupRequest request) throws DataNotFoundException, DataAlreadyExistException;

    Result<Boolean> activeAccount(String id) throws DataNotFoundException;

    String forgotPassword(ForgotAccountRequest request) throws DataNotFoundException;
}
