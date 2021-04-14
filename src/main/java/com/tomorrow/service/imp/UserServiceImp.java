package com.tomorrow.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tomorrow.dao.UserDao;
import com.tomorrow.entity.User;
import com.tomorrow.service.UserService;
import com.tomorrow.util.RedisUtil;
import com.tomorrow.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public String login(User user) {
        if(user == null){
            return null;
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("userId", user.getUserId());
        queryWrapper.eq("password", user.getPassword());
        user = userDao.selectOne(queryWrapper);
        if(user != null){
            String token = TokenUtil.geneToken(user); //生成token
            RedisUtil.setString(user.getUserId(),token); //存进redis
            return token;
        }
        return null;
    }

    /**
     * 增删查改
     * */
    @Override
    public User findUserById(String userId) {
        User user = null;
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("userId", userId);
        user = userDao.selectOne(queryWrapper);
        return user;
    }

    @Override
    public User updataUser(User user) {
        return null;
    }

    @Override
    public User delUser(User user) {
        return null;
    }

    @Override
    public User addUser(User user) {
        return null;
    }
}
