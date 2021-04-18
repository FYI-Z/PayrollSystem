package com.tomorrow.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tomorrow.dao.UserDao;
import com.tomorrow.entity.User;
import com.tomorrow.service.RedisService;
import com.tomorrow.service.TokenService;
import com.tomorrow.service.UserService;
import com.tomorrow.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

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
    public List<User> findAllUserInfo() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.select("userId","name","age","sex","phone","department","position","permission","status");
        List<User> list= userDao.selectList(queryWrapper);
        return list;
    }


    @Override
    public int addUser() {
        User user = null;
        String userid = "" + Constant.YEAR + (new Random().nextInt(10000)+1000);
        user = userDao.selectById(userid);
        while(user!=null){
            userid = "" + Constant.YEAR + (new Random().nextInt(10000)+1000);
            user = userDao.selectById(userid);
        }
        try{
            user = new User();
            user.setUserId(""+userid).setPassword("123456").setPermission("0,0,0,0,0");
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
        userDao.insert(user);
        return 1;
    }

    @Override
    public List<User> findAllUserPower() {
        User user = null;
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.select("userId","name","permission");
        List<User> list= userDao.selectList(queryWrapper);
        return list;
    }

    @Override
    public int updateUserPower(String userId, String power) {
        User user = userDao.selectById(userId);
        user.setPermission(power);
        return userDao.updateById(user);
    }

    @Override
    public int updateUserStatus(String id,int status) {
        User user = userDao.selectById(id);
        user.setStatus(status);
        return userDao.updateById(user);
    }


    @Override
    public int updateUserDepart(String id,String department) {
        User user = userDao.selectById(id);
        user.setDepartment(department);
        return userDao.updateById(user);
    }

    @Override
    public int delUser(String id) {
        return userDao.deleteById(id);
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

    @Override
    public List<User> findUserPower(String value) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("userid",value);
        queryWrapper.select("userId","name","permission");
        List<User> list= userDao.selectList(queryWrapper);
        return list;
    }
}
