package com.example.gtvtbe.security;

import com.example.gtvtbe.common.Result;
import com.example.gtvtbe.model.response.JwtInformationResponse;
import com.example.gtvtbe.security.domain.AccountInformation;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Component
@Transactional
@Slf4j
public class TokenJWT {
    @Value("${key-jwt-secret}")
    private String keyJWTSecret;
    @Value("${expiration-jwt-secret}")
    private Long expirationJWT;
    @Value("${type-token-jwt-secret}")
    private String typeJWTSecret;
    public Result<JwtInformationResponse> generateToken(Authentication authentication) {
        try {
            Long timestamp = System.currentTimeMillis();
            AccountInformation accountInformation = (AccountInformation) authentication.getPrincipal();
            String token = Jwts.builder()
                    .signWith(getSecretKey())
                    .setSubject("huy")
                    .setClaims(getRoles(authentication))
                    .setExpiration(new Date(expirationJWT + timestamp))
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .claim("username",accountInformation.getUsername())
                    .compact();
            log.info("Token created with username : {}",accountInformation.getUsername());
            return Result.ok(
                    new JwtInformationResponse(token, timestamp + expirationJWT, typeJWTSecret, authentication.getName())
            );
        } catch (JwtException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<String, List<String>> getRoles(Authentication authentication) {
        Map<String, List<String>> map = new HashMap<>();
        List<String> roles = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority).toList();
        map.put("roles", roles);
        return map;
    }

    public Jws<Claims> verify(String token) {
         var jws = Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token);
        return jws;
    }

    public SecretKey getSecretKey() {
        return new SecretKeySpec(keyJWTSecret.getBytes(StandardCharsets.UTF_8), SignatureAlgorithm.HS256.getJcaName());
    }
}
