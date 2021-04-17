package com.tomorrow.service;
/**
 *
 * */
import com.tomorrow.entity.Attendance;
import java.util.List;

public interface AttendanceService {
    /**
     * 增删查改
     * */
    public List<Attendance> getAll();
    public List<Attendance> getCount(int page, int limit);
    public List<Attendance> findAll(String key);
    public Attendance findAttendanceById(String attendanceid);
    public List<Attendance> findAttendanceByUserId(String userid);
    public int updataAttendance(Attendance attendance);
    public List<Attendance> delAttendance(String[] list);
    public Attendance addAttendance(Attendance attendance);
}

