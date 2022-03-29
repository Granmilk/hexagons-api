package com.gtbr.hexapi.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.gtbr.hexapi.entity.UserProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TokenService {

    @Value("${jwt.secret}")
    private String secret;

    public String encode(UserProfile userProfile) {
        return JWT.create()
                .withClaim("userId", userProfile.getUserUUID().toString())
                .withClaim("email", userProfile.getEmail())
                .withClaim("roles", List.of("PLAYER"))
                .withIssuer("hexagons-api")
                .sign(Algorithm.HMAC256(secret));
    }
}
