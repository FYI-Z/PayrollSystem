package com.tomorrow.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tomorrow.dao.AttendanceDao;
import com.tomorrow.entity.Attendance;
import com.tomorrow.service.AttendanceService;
import com.tomorrow.vo.AttendanceResultVo;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class AttendanceServiceImp implements AttendanceService {
    AttendanceResultVo attendanceResultVo = new AttendanceResultVo();
    @Autowired
    private AttendanceDao attendanceDao;
    /*@Test
    public void test(){
        SimpleDateFormat formatter= new SimpleDateFormat("yyyyMMdd");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date));
    }*/

    @Override
    public List<Attendance> showAll(int current, int size) {
        QueryWrapper<Attendance> queryWrapper = new QueryWrapper<>();
        List<Attendance> attendanceList = new ArrayList<>();
        IPage<Attendance> page = new Page<>(current,size);
        page = attendanceDao.selectPage(page, queryWrapper);
        page.getRecords().forEach(attendance -> attendanceList.add(attendance));
        return attendanceList;
    }

    @Override
    public Attendance findAttendanceById(String attendanceid) {
        Attendance attendance = null;
        attendance = attendanceDao.selectById(attendanceid);
        return attendance;
    }

    @Override
    public Attendance updataAttendance(Attendance attendance) {
        return null;
    }

    @Override
    public List<Attendance> delAttendance(List<String> list) {
        List<Attendance> attendanceList = new ArrayList<>();
        if(list == null){
            return null;
        }
        for(String string : list){
            attendanceList.add(attendanceDao.selectById(string));
            attendanceDao.deleteById(string);
        }
        return attendanceList;
    }

    @Override
    public Attendance addAttendance(Attendance attendance) {
        if(attendance == null){
            attendanceResultVo.setRetmsg("数据为空");
            return attendance;
        }
        SimpleDateFormat formatter= new SimpleDateFormat("yyyyMMdd");
        Date date = new Date(System.currentTimeMillis());
        attendance.setAttendanceid(formatter.format(date) + attendance.getUserid());
        if(attendanceDao.selectById(attendance.getAttendanceid()) != null){
            //return "该用户当天考勤数据已存在";
            attendanceResultVo.setRetmsg("该用户当天考勤数据已存在");
            return attendance;
        }
        formatter= new SimpleDateFormat("yyyy年MM月dd日");
        attendance.setAttendanceTime(formatter.format(date));
        attendanceDao.insert(attendance);
        //return "添加成功";
        attendanceResultVo.setRetmsg("添加成功");
        return attendance;
    }

}
