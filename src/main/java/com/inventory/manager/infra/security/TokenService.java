package com.inventory.manager.infra.security;

import com.auth0.jwt.algorithms.Algorithm;
import com.inventory.manager.domain.users.Users;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Value("${api.security.token.secrect}")
    private String secret;

    public String generateToken (Users user){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "";
    }
}
