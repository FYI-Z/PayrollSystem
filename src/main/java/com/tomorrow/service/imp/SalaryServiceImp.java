package com.tomorrow.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tomorrow.dao.*;
import com.tomorrow.entity.*;
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
    @Autowired
    public RecordDao recordDao;
    @Autowired
    public AttendanceDao attendanceDao;
    @Autowired
    public StandardDao standardDao;

    @Override
    public boolean countSalary(String userId , double commission , double bonus) {

        System.out.println(userId);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("userid",userId);

        Salary salary;
        Attendance attendance;
        Standard standard;
        User user;
        try {
            /*获取薪资数据*/
            salary = salaryDao.selectOne(queryWrapper);
            /*获取考勤数据*/
            attendance = attendanceDao.selectOne(queryWrapper);
            /*获取薪资标准数据*/
            standard = standardDao.selectList(null).get(0);
            /*获取用户数据*/
            user = userDao.selectOne(queryWrapper);
        }catch (Exception e){
            System.out.println("userid不存在");
            return false;
        }
        try{
            /*迟到扣新*/
            double late = attendance.getLateHours() * standard.getLateStandard();
            salary.setLate(late);
            /*旷工扣薪*/
            double absenteeism = attendance.getAbsenteeismDays() * standard.getAbsenteeismStandard();
            salary.setAbsenteeism(absenteeism);
            /*请假扣新*/
            double vacate = attendance.getLeaveHours() * standard.getAbsenteeismStandard();
            salary.setVacate(vacate);
            /*加班薪资*/
            double overtime = attendance.usualOvertimeHours * standard.getOvertimeStandard()
                    + attendance.weekendOvertimeHours * standard.getOvertimeStandard() * 2
                    + attendance.festivalOvertimeHours * standard.getOvertimeStandard() * 3;
            salary.setOvertime(overtime);
            /*提成薪资*/
            if("市场部门".equals(user.department)){
                if(salary.getCommission() != 0){
                    salary.setCommission(commission);
                }
            }else{
                salary.setCommission(0);
            }
            /*奖金*/
            if(salary.getBonus() != 0){
                salary.setBonus(bonus);
            }
            /*实发工资*/
            double actual = salary.getBasicSalary() + salary.getOvertime()
                    - salary.getLate() - salary.getAbsenteeism() - salary.getVacate();
            salary.setActual(actual);

            int flag = salaryDao.update(salary,queryWrapper);
            return flag != 0;
        }catch (Exception e){
            System.out.println("userid不存在");
        }
        return false;

    }

    @Override
    public List<Salary> showSalary(String token, String userId) {
        List<Salary> salaryList = new ArrayList<>();
        TokenServiceImp tokenServiceImp = new TokenServiceImp();
        String department = tokenServiceImp.parseToken(token).get("department",String.class);
        String position = tokenServiceImp.parseToken(token).get("position",String.class);
        QueryWrapper queryWrapper = new QueryWrapper();
        if("人力资源部门".equals(department) || "财务部门".equals(department)){
            return findAllSalary();
        }else if("部门经理".equals(position)){
            return findSalaryByDep(department);
        } else {
            return findSalaryByOne("userid",userId);
        }
    }

    @Override
    public List<Salary> findSalaryByDep(String department) {
        QueryWrapper<User> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("department",department);
        List<User> users = userDao.selectList(queryWrapper1);
        List<Salary> salaryList = new ArrayList<>();

        QueryWrapper<Salary> queryWrapper2 = new QueryWrapper<>();
        for(int i = 0 ; i < users.size() ; i++){
            queryWrapper2.eq("userid", users.get(i).getUserId());
            Salary salary = salaryDao.selectOne(queryWrapper2);
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
        if(salary.getUserid() == null){
            return false;
        }

        /*生成salaryid*/
        String id = StringUtil.getUUID();
        while(salaryDao.selectById(id) != null){
            id = StringUtil.getUUID();
        }

        /*查询该userid在userinfo以及salary中是否存在*/
        try {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("userid",salary.getUserid());
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
