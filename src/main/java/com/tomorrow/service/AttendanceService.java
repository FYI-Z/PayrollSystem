
package com.tomorrow.service;

import com.tomorrow.entity.Attendance;

import java.util.List;

public interface AttendanceService {
    public List<Attendance> showAll(int current, int size);

    /**
     * 增删查改
     * */

    public Attendance findAttendanceById(String attendanceid);
    public Attendance updataAttendance(Attendance attendance);
    public List<Attendance> delAttendance(List<String> list);
    public Attendance addAttendance(Attendance attendance);


}

