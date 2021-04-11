package com.tomorrow.service;

import com.tomorrow.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public String login(User user);
   /**
   * 增删查改
   * */
    public User findUserById(String userId);
    public User updataUser(User user);
    public User delUser(User user);
    public User addUser(User user);
}
