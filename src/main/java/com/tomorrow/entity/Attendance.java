package com.tomorrow.entity;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Transient;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
@TableName(value = "attendance")
public class Attendance {
    @TableId(value = "attendanceid")
    public String attendanceid;
    @TableField("userid")
    public String userid;
    @JsonProperty("leave_hours")
    public Double leaveHours;
    @JsonProperty("late_hours")
    public Double lateHours;
    @JsonProperty("absenteeism_days")
    public Integer absenteeismDays;
    @JsonProperty("usual_overtime_hours")
    public Double usualOvertimeHours;
    @JsonProperty("weekend_overtime_hours")
    public Double weekendOvertimeHours;
    @JsonProperty("festival_overtime_hours")
    public Double festivalOvertimeHours;
    @JsonProperty("attendance_time")
    public String attendanceTime;
}
