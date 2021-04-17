package com.tomorrow.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tomorrow.dao.AttendanceDao;
import com.tomorrow.entity.Attendance;
import com.tomorrow.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AttendanceServiceImp implements AttendanceService {
    @Autowired
    private AttendanceDao attendanceDao;

    @Override
    public List<Attendance> getAll() {
        List<Attendance> attendances = attendanceDao.selectList(null);
        return attendances;
    }
    //分页取出考勤记录
    @Override
    public List<Attendance> getCount(int current, int size) {
        QueryWrapper<Attendance> queryWrapper = new QueryWrapper<>();
        List<Attendance> attendanceList = new ArrayList<>();
        IPage<Attendance> page = new Page<>(current,size);
        page = attendanceDao.selectPage(page, queryWrapper);
        page.getRecords().forEach(attendance -> attendanceList.add(attendance));
        return attendanceList;
    }

    //模糊查询
    @Override
    public List<Attendance> findAll(String key) {
        if(key == null){
            return null;
        }
        QueryWrapper<Attendance> queryWrapper = new QueryWrapper<>();
        queryWrapper.and(
                wrapper ->
                        wrapper.like("attendanceid",key).or().like("userid",key).or().like("leave_hours",key)
                                .or().like("late_hours",key).or().like("absenteeism_days",key).or().like("usual_overtime_hours",key)
                                .or().like("weekend_overtime_hours",key).or().like("festival_overtime_hours",key).or().like("attendance_time",key)
        );
        List<Attendance> attendanceList = attendanceDao.selectList(queryWrapper);
        return attendanceList;
    }
    //通过考勤ID查考勤记录
    @Override
    public Attendance findAttendanceById(String attendanceid) {
        Attendance attendance = null;
        attendance = attendanceDao.selectById(attendanceid);
        return attendance;
    }
    //通过用户ID查考勤记录
    @Override
    public List<Attendance> findAttendanceByUserId(String userid) {
        Attendance attendance = null;
        QueryWrapper<Attendance> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid",userid);//设置等值查询
        List<Attendance> attendanceList = attendanceDao.selectList(queryWrapper);
        return attendanceList;
    }

    //修改考勤记录
    @Override
    public int updataAttendance(Attendance attendance) {
        int count = 0;
        if(attendance.getAttendanceid() == null || attendance.getUserid() == null){
            return count;
        }
        count = attendanceDao.updateById(attendance);
        return count;
    }
    //删除考勤记录
    @Override
    public List<Attendance> delAttendance(String [] list) {
        List<Attendance> attendanceList = new ArrayList<>();
        if(list == null){
            return null;
        }
        for(String string : list){
            attendanceDao.deleteById(string);
        }
        List<Attendance> attendances = attendanceDao.selectList(null);
        return attendances;
    }
    //添加考勤记录
    @Override
    public Attendance addAttendance(Attendance attendance) {
        if(attendance.getUserid() == null){
            return null;
        }
        SimpleDateFormat formatter= new SimpleDateFormat("yyyyMM");
        Date date = new Date(System.currentTimeMillis());
        attendance.setAttendanceid(formatter.format(date) + attendance.getUserid());
        if(attendanceDao.selectById(attendance.getAttendanceid()) != null){     //该用户当天考勤数据已存在
            return null;
        }
        formatter= new SimpleDateFormat("yyyy年MM月");
        attendance.setAttendanceTime(formatter.format(date));
        attendanceDao.insert(attendance);
        return attendance;
    }
}
