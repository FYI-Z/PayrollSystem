package com.tomorrow.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tomorrow.dao.AttendanceDao;
import com.tomorrow.dao.UserDao;
import com.tomorrow.entity.Attendance;
import com.tomorrow.entity.Record;
import com.tomorrow.entity.Salary;
import com.tomorrow.entity.User;
import com.tomorrow.service.*;
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
    @Autowired
    private SalaryService salaryService;
    @Autowired
    private RecordService recordService;
    @Autowired
    private AttendanceService attendanceService;
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
    public int addUser(int count) {
        int t = count;
        while(count!=0){
            count--;
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
            //新增薪资
            Salary salary = new Salary();
            salary.setUserid(user.getUserId());
            salaryService.addSalary(salary);

            //新增奖惩
            Record record = new Record();
            record.setUserid(user.getUserId());
            recordService.addRecord(record);

            //新增考勤
            Attendance attendance = new Attendance();
            attendance.setUserid(user.getUserId());
            attendanceService.addAttendance(attendance);
        }
        return t;
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
    public List<User> findUserByDepart(String depart) {
        User user = null;
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.select("userId","name","age","sex","phone","department","position","permission","status");
        queryWrapper.eq("department",depart);
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
    public int updateUser(User user) {
        return userDao.updateById(user);
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
