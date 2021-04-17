package com.tomorrow.service;

import org.springframework.stereotype.Service;

@Service
public interface CheckService {
    public int checkToken(String userid,String token);
    public int checkPower(String userid,int power);
}
