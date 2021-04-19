package com.tomorrow.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tomorrow.dao.StandardDao;
import com.tomorrow.dao.UserDao;
import com.tomorrow.entity.Salary;
import com.tomorrow.entity.Standard;
import com.tomorrow.entity.User;
import com.tomorrow.service.StandardService;
import jdk.nashorn.internal.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
public class StandardServiceImp implements StandardService {
    @Autowired
    private StandardDao standardDao;
    @Autowired
    private SalaryServiceImp salaryServiceImp;
    @Autowired
    private UserDao userDao;
    @Autowired
    private TokenServiceImp tokenServiceImp;

    @Override
    public Standard findAllStandard() {
        List<Standard> standards = standardDao.selectList(null);
        try {
            Standard standard = standards.get(0);
            return standard;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public boolean updateStandard(Standard standard , String token) {

        String department = tokenServiceImp.parseToken(token).get("department",String.class);
        System.out.println(department);
        if(!"人力资源部".equals(department)){
            return false;
        }

        Standard s = standardDao.selectList(null).get(0);
        if(standard.getLateStandard() != 0.0) s.setLateStandard(standard.getLateStandard());
        if(standard.getOvertimeStandard() != 0.0) s.setOvertimeStandard(standard.getOvertimeStandard());
        if(standard.getAbsenteeismStandard() != 0) s.setAbsenteeismStandard(standard.getAbsenteeismStandard());
        int flag = standardDao.update(s , null);
        if(flag != 0){
            List<User> users = userDao.selectList(null);
            users.forEach(user -> {
                salaryServiceImp.countSalary(user.getUserId() , 0 , 0);
            });
        }
        return flag != 0;
    }
}
