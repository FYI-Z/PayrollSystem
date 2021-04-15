package com.tomorrow.controller;

import com.tomorrow.entity.Attendance;
import com.tomorrow.service.AttendanceService;
import com.tomorrow.util.Constant;
import com.tomorrow.util.ResultUtil;
import com.tomorrow.vo.AttendanceResultVo;
import com.tomorrow.vo.LoginResultVo;
import com.tomorrow.vo.ReturnResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/PayrollSystem/Attendance")
public class AttendanceController {
    AttendanceResultVo attendanceResultVo = new AttendanceResultVo();
    @Autowired
    private AttendanceService attendanceService;
    @ApiOperation(value = "考勤数据操作接口")
    @ApiImplicitParam()
    /**
     *
     * */
    @PostMapping("/showAll")
    public ReturnResult showAll(@RequestParam int current, @RequestParam int size){
        if(size == 0){
            return ResultUtil.error(Constant.RESCODE_EXCEPTION,"参数错误");
        }
        List<Attendance> attendanceList = attendanceService.showAll(current,size);
        return ResultUtil.success(attendanceList,Constant.RESCODE_SUCCESS,attendanceList.size());
    }

    @PostMapping("/find")
    public ReturnResult findAttendanceById(@RequestBody Attendance attendance){
       if (attendance.getAttendanceid() == null){
           return ResultUtil.error(Constant.RESCODE_EXCEPTION,"考勤ID为空");
       }
       attendance = attendanceService.findAttendanceById(attendance.getAttendanceid());
       if(attendance == null){
           return ResultUtil.error(Constant.RESCODE_NOEXIST,"查询结果为空");
       }
       return ResultUtil.success(attendance, Constant.RESCODE_SUCCESS,1);
    }

    @PostMapping("/updata")
    public ReturnResult updataAttendance(@RequestBody Attendance attendance){

        return null;
    }

    @PostMapping("/del")
    public ReturnResult delAttendance(@RequestBody List<String>  list){
        if (list.size() == 0){
            return ResultUtil.error(Constant.RESCODE_EXCEPTION,"没有需要删除的数据");
        }
        List<Attendance> attendanceList = attendanceService.delAttendance(list);
        if(attendanceList == null){
            return ResultUtil.error(Constant.RESCODE_DELETEERROR,"删除失败");
        }
        return ResultUtil.success(attendanceList, Constant.RESCODE_SUCCESS,attendanceList.size());
    }

    @PostMapping("/add")
    public ReturnResult addAttendance(@RequestBody Attendance attendance){
        if(attendance == null){
            return ResultUtil.error(Constant.RESCODE_INSERTERROR,"添加失败");
        }
        attendance = attendanceService.addAttendance(attendance);
        attendanceResultVo.setAttendance(attendance);
        return ResultUtil.success(attendanceResultVo, Constant.RESCODE_SUCCESS,1);
    }

}
