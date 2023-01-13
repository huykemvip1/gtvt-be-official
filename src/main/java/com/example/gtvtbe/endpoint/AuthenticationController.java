package com.example.gtvtbe.endpoint;


import com.example.gtvtbe.common.Result;
import com.example.gtvtbe.exception.DataAlreadyExistException;
import com.example.gtvtbe.exception.DataNotFoundException;
import com.example.gtvtbe.model.request.ForgotAccountRequest;
import com.example.gtvtbe.model.request.SigninRequest;
import com.example.gtvtbe.model.request.SignupRequest;
import com.example.gtvtbe.security.TokenJWT;
import com.example.gtvtbe.service.AccountService;
import com.example.gtvtbe.util.Constant;
import com.example.gtvtbe.util.CustomSendMail;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/authentication")
@Api(value = "Where to manage authentication and account signup.")
@Slf4j
@CrossOrigin
public class AuthenticationController {

    private static final String URL_VERIFY="/api/v1/authentication/active/";
    @Autowired
    private MessageSource messageSource;

    private final AccountService accountService;

    private final TokenJWT tokenJWT;

    private final AuthenticationProvider authenticationProvider;

    private final CustomSendMail customSendMail;

    @Autowired
    public AuthenticationController(AccountService accountService, TokenJWT tokenJWT, AuthenticationProvider authenticationProvider, CustomSendMail customSendMail) {
        this.accountService = accountService;
        this.tokenJWT = tokenJWT;
        this.authenticationProvider = authenticationProvider;
        this.customSendMail = customSendMail;
    }

    @PostMapping(value = "/signin", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> signIn(@Valid @RequestBody SigninRequest request) {
        log.info("{}",request.getPassword());
        Authentication authentication = authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        var result = tokenJWT.generateToken(authentication);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/signup")
    public ResponseEntity<Result<?>> signUp(HttpServletRequest rq, @Valid @RequestBody SignupRequest request) throws DataNotFoundException, DataAlreadyExistException, MessagingException, IOException {
        log.info("/authentication/signUp");
        var result = accountService.signUp(request);
        StringBuilder url = new StringBuilder();
        url.append(rq.getScheme())
                .append("://")
                .append(rq.getServerName())
                .append(":")
                .append(rq.getServerPort())
                .append(URL_VERIFY)
                .append(result.getData());
//        customSendMail.sendMail(request.getEmailSecurity(), Constant.SENDER, url.toString());
        return ResponseEntity.ok(result);
    }
    @GetMapping("/active/{id}")
    public ResponseEntity<Result<?>> activeAccount(@PathVariable String id) throws DataNotFoundException {
        if (id == null){
            return ResponseEntity.ok(Result.badRequest(messageSource.getMessage("entity.not.found",new Object[]{id},null)));
        }
        var result = accountService.activeAccount(id);
        return ResponseEntity.ok(Result.ok(true));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<Result<?>> forgotPassword(@Valid @RequestBody ForgotAccountRequest request) throws DataNotFoundException, MessagingException, IOException {
        var result = accountService.forgotPassword(request);
        customSendMail.resetPassword(request.getEmail(), Constant.SENDER, result);
        return ResponseEntity.ok(Result.ok(true));
    }
}
