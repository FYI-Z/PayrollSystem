package com.tomorrow.service;

import com.tomorrow.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public String login(User user);

   /**
   * 增删查改
   * */
    public User findUserById(String userId);
    public List<User> findAllUserPower();
    public int updateUserPower(String userId,String power);
    public User updataUser(User user);
    public User delUser(User user);
    public User addUser(User user);

    List<User> findUserPower(String value);
}
