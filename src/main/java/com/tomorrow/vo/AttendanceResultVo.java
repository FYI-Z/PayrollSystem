package com.tomorrow.vo;

import com.tomorrow.entity.Attendance;
import com.tomorrow.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class AttendanceResultVo {
    private Attendance attendance;
    private String retmsg;
}
