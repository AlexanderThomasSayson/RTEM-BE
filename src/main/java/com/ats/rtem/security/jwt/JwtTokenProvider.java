package com.ats.rtem.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Data
@Component
@ConfigurationProperties(prefix = "app")
public class JwtTokenProvider {

    private String jwtSecret;
    private long jwtExpirationMilliseconds;

    // Generate the JWT token
    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expiredDate = new Date(currentDate.getTime() + jwtExpirationMilliseconds);

        return Jwts.builder()
                .subject(username)
                .issuedAt(currentDate)
                .expiration(expiredDate)
                .signWith(getSigningKey())
                .compact();
    }

    // Convert and encode the secret key from Base64
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    // Extract username from the token
    public String getUsername(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getSubject();
    }

    // Validate the JWT token
    public boolean isValidToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;  // Token is invalid or expired
        }
    }
}