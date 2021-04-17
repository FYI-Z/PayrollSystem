package com.tomorrow.service;

import com.tomorrow.entity.User;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;

@Service
public interface TokenService {
    public Claims parseToken(String token);
    public String geneToken(User user);
}
