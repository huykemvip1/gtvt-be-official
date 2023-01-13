package com.example.gtvtbe.exception;

import com.example.gtvtbe.common.Result;
import com.example.gtvtbe.enumeration.EnumResponseStatus;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.example.gtvtbe.enumeration.EnumResponseStatus.CODE_401;

@ControllerAdvice
@Slf4j
public class RestException{

    private final MessageSource messageSource;

    @Autowired
    public RestException(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<?> unAuthenticated(AuthenticationException e) {
        return ResponseEntity
                .ok()
                .body(Result.responseStatus(messageSource.getMessage("account.not.unauthenticated",null,null),CODE_401));
    }
    @ExceptionHandler({Exception.class})
    public ResponseEntity<?> unAuthenticated(Exception e) {
        log.info(e.getMessage());
        return ResponseEntity
                .ok()
                .body(Result.badRequest(e.getMessage()));
    }
//    @ExceptionHandler({RuntimeException.class})
//    public ResponseEntity<?> jwtErrors(RuntimeException e) {
//        log.error("{}",e.getMessage());
//        return ResponseEntity
//                .ok()
//                .body(Result.responseStatus(e.getMessage(), EnumResponseStatus.CODE_400));
//    }
}
