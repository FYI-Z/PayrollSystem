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
    public List<User> findAllUserInfo();
    public List<User> findAllUserPower();
    public List<User> findUserByDepart(String depart);
    public int addUser(int count);
    public int updateUserPower(String userId,String power);
    public int updateUserStatus(String id, int status);
    public int updateUserDepart(String id,String department);
    public int delUser(String id);
    public int updateUser(User user);
    public User delUser(User user);
    public User addUser(User user);

    List<User> findUserPower(String value);
}
