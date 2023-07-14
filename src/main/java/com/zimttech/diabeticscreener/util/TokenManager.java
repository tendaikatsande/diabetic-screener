package com.zimttech.diabeticscreener.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class TokenManager implements Serializable {
    public static final long TOKEN_VALIDITY = 10 * 60 * 60;

    private static final long REFRESH_TOKEN_VALIDITY = 7 * 24 * 60 * 60 * 1000; // 7 days in milliseconds


    private static final long serialVersionUID = 7008375124389347049L;
    @Value("${spring.jwt.secret}")
    private String jwtSecret;

    public String generateJwtToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", userDetails.getAuthorities());
        return Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }

    public String generateRefreshToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", userDetails.getAuthorities());
        return Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }


    public Boolean validateJwtToken(String token, UserDetails userDetails) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        Boolean isTokenExpired = claims.getExpiration().before(new Date());
        Boolean isTokenRevoked = claims.get("is_revoked", Boolean.class);
        return (claims.getSubject().equals(userDetails.getUsername()) && !isTokenExpired && !Boolean.TRUE.equals(isTokenRevoked));
    }

    public Boolean verifyJwtToken(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
            Boolean isTokenExpired = claims.getExpiration().before(new Date());
            Boolean isTokenRevoked = claims.get("is_revoked", Boolean.class);
            return !isTokenExpired && !Boolean.TRUE.equals(isTokenRevoked);
        } catch (Exception e) {
            return false;
        }
    }


    public String getUsernameFromToken(String token) {
        final Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public String extractRefreshTokenFromRequest(HttpServletRequest request) {
        return null;
    }

    public boolean validateRefreshToken(String refreshToken) {
        return false;
    }
}


