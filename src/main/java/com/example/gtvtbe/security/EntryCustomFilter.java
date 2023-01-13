package com.example.gtvtbe.security;

import com.example.gtvtbe.enumeration.EnumURLInogred;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.description.type.TypeList;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class EntryCustomFilter extends OncePerRequestFilter {
    private final TokenJWT tokenJwt;

    public EntryCustomFilter(TokenJWT tokenJwt) {
        this.tokenJwt = tokenJwt;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        log.info(request.getServletPath());
        try {
            String token = request.getHeader(HttpHeaders.AUTHORIZATION);
            token = token.substring("Bearer ".length());
            // verify token
            Jws<Claims> jws = tokenJwt.verify(token);
            ObjectMapper objectMapper = new ObjectMapper();
            // convert token to roles
            @SuppressWarnings("unchecked") List<String> roles =  (List<String>) jws.getBody().get("roles");
            Collection<? extends GrantedAuthority> getAuthorities = roles
                    .stream()
                    .map(SimpleGrantedAuthority::new).toList();
            // convert to token to username
            String getUsername = jws.getBody().get("username").toString();
            Authentication authentication = new UsernamePasswordAuthenticationToken(getUsername,null,getAuthorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        } catch (RuntimeException e) {
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        for (EnumURLInogred inogred : EnumURLInogred.values()){
            if (request.getRequestURI().startsWith(inogred.getValue())){
                return true;
            }
        }
        return false;
    }
}
