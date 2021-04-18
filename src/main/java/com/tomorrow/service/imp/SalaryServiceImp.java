package com.tomorrow.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tomorrow.dao.SalaryDao;
import com.tomorrow.dao.UserDao;
import com.tomorrow.entity.Salary;
import com.tomorrow.entity.User;
import com.tomorrow.service.SalaryService;
import com.tomorrow.util.StringUtil;
import com.tomorrow.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SalaryServiceImp implements SalaryService {

    @Autowired
    public SalaryDao salaryDao;
    @Autowired
    public UserDao userDao;

    @Override
    public List<Salary> findSalaryByDep(String department) {
        QueryWrapper<User> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("department",department);
        List<User> users = userDao.selectList(queryWrapper1);
        List<Salary> salaryList = new ArrayList<>();

        System.out.println(users.size());
        QueryWrapper<Salary> queryWrapper2 = new QueryWrapper<>();
        for(int i = 0 ; i < users.size() ; i++){
            queryWrapper2.eq("userid", users.get(i).getUserId());
            Salary salary = salaryDao.selectOne(queryWrapper2);
            System.out.println(salary.getUserid());
            salaryList.add(salary);
        }
        return salaryList;
    }

    @Override
    public List<Salary> findSalaryByOne(String key, String value) {
        QueryWrapper<Salary> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(key,value);//设置等值查询
        List<Salary> salaryList = salaryDao.selectList(queryWrapper);
        return salaryList;
    }

    @Override
    public List<Salary> findAllSalary() {
        List<Salary> salaryList = new ArrayList<Salary>();
        List<Salary> salarys = salaryDao.selectList(null);
        salarys.forEach(salary-> salaryList.add(salary));
        return salaryList;
    }

    @Override
    public Salary findSalaryById(String salaryId) {
        Salary salary = new Salary();
        QueryWrapper<Salary> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("salaryid",salaryId);//设置等值查询
        salary = salaryDao.selectById(salaryId);
        return salary;
    }

    @Override
    public boolean updateSalary(Salary salary) {
        Salary sa = salaryDao.selectById(salary.getSalaryid());

        if(salary.getUserid() != null )  sa.setUserid(salary.getUserid());
        if(salary.getApplySalary() != null )  sa.setApplySalary(salary.getApplySalary());
        if(salary.getApplicant() != null )  sa.setApplicant(salary.getApplicant());
        if(salary.getApplyStatus() != null )  sa.setApplyStatus(salary.getApplyStatus());
        if(salary.getSalaryStatus() != null )  sa.setSalaryStatus(salary.getSalaryStatus());
        if(salary.getBasicSalary() != 0.0 )  sa.setBasicSalary(salary.getBasicSalary());
        if(salary.getOvertime() != 0.0 )  sa.setOvertime(salary.getOvertime());
        if(salary.getCommission() != 0.0 )  sa.setCommission(salary.getCommission());
        if(salary.getBonus() != 0.0 )  sa.setBonus(salary.getBonus());
        if(salary.getVacate() != 0.0 )  sa.setVacate(salary.getVacate());
        if(salary.getLate() != 0.0 )  sa.setLate(salary.getLate());
        if(salary.getAbsenteeism() != 0 )  sa.setAbsenteeism(salary.getAbsenteeism());
        if(salary.getActual() != 0.0 )  sa.setActual(salary.getActual());
        sa.setSalaryTime(TimeUtil.getTime());

        int flag = salaryDao.updateById(sa);
        return flag != 0;
    }

    @Override
    public boolean deleteSalary(String salaryId) {
        Salary salary = new Salary();
        QueryWrapper<Salary> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(true,Salary::getSalaryid,salaryId);
        int flag  = salaryDao.delete(queryWrapper);
        return flag != 0;
    }

    @Override
    public boolean addSalary(Salary salary) {
        /*生成salaryid*/
        String id = StringUtil.getUUID();
        while(salaryDao.selectById(id) != null){
            id = StringUtil.getUUID();
        }

        /*查询该userid在userinfo以及salary中是否存在*/
        try {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("userid",salary.getUserid());
//            System.out.println(salaryDao.selectOne(queryWrapper).getUserid());
            if(userDao.selectOne(queryWrapper) == null || salaryDao.selectOne(queryWrapper) != null){
                return false;
            }
        }catch (Exception e){
            return false;
        }

        salary.setSalaryid(id);
        salary.setSalaryTime(TimeUtil.getTime());
        int flag = salaryDao.insert(salary);
        return flag != 0;
    }
}
