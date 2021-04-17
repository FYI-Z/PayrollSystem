package com.tomorrow.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tomorrow.dao.UserDao;
import com.tomorrow.entity.User;
import com.tomorrow.service.RedisService;
import com.tomorrow.service.TokenService;
import com.tomorrow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private RedisService redisService;
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
            String token = tokenService.geneToken(user); //生成token
            redisService.setString(user.getUserId(),token); //存进redis
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
    public List<User> findAllUserPower() {
        User user = null;
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.select("userid","permission");
        List<User> list= userDao.selectList(queryWrapper);
        return list;
    }

    @Override
    public int updateUserPower(String userId, String power) {
        User user = userDao.selectById(userId);
        user.setPermission(power);
        QueryWrapper<User> updateWrapper = new QueryWrapper<User>();
        updateWrapper.eq("userid",userId);
        return userDao.update(user, updateWrapper);
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
