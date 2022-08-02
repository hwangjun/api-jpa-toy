package dev.backend.hw.domain.auth.service;

import org.springframework.stereotype.Service;

@Service
public class TokenService {


    public boolean validateToken(String userPk, String token) {
        return true;
    }
}
