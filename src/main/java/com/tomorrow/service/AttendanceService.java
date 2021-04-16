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
    public List<Attendance> showAll(int current, int size);
    public List<Attendance> findAll(String key);
    public Attendance findAttendanceById(String attendanceid);
    public int updataAttendance(Attendance attendance);
    public List<Attendance> delAttendance(List<String> list);
    public Attendance addAttendance(Attendance attendance);
}

