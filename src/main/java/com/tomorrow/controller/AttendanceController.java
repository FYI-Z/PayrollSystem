package com.tomorrow.controller;

import com.tomorrow.entity.Attendance;
import com.tomorrow.service.AttendanceService;
import com.tomorrow.util.Constant;
import com.tomorrow.util.ResultUtil;
import com.tomorrow.vo.ReturnResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * @Author 李章
 * @Description 考勤数据操作接口
 * @Date 16:05 2021/4/17
 **/

@CrossOrigin
@RestController
@RequestMapping("/PayrollSystem/Attendance")
public class AttendanceController {
    @Autowired
    private AttendanceService attendanceService;
    @ApiOperation(value = "考勤数据操作接口")
    @ApiImplicitParam()

    //获取全部考勤记录
    @ResponseBody
    @PostMapping("/getAll")
    public Map<String,Object> getAll(@RequestParam int page, @RequestParam int limit){
        Map<String,Object> tableData =new HashMap<String,Object>();
        if(page == 0 || limit ==0){
            tableData.put("code", Constant.RESCODE_EXCEPTION);
            tableData.put("msg", "error");
            tableData.put("count", 0);
            tableData.put("data", null);
            return tableData;
        }
        //获取全部考勤记录
        List<Attendance> attendances = attendanceService.getAll();
        //获取分页后的每页考勤记录
        List<Attendance> attendanceList = attendanceService.getCount(page,limit);

        tableData.put("code", Constant.RESCODE_SUCCESS);
        tableData.put("msg", "success");
        //将全部数据的条数作为count传给前台（一共多少条）
        tableData.put("count", attendances.size());
        //将分页后的数据返回（每页要显示的数据）
        tableData.put("data", attendanceList);
        return tableData;
    }
    //通过考勤ID获取考勤记录
    @PostMapping("/findById")
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
    //通过用户ID获取考勤记录
    @PostMapping("/findByUserId")
    public ReturnResult findAttendanceByUserId(@RequestBody Attendance attendance){
        if (attendance.getUserid() == null){
            return ResultUtil.error(Constant.RESCODE_EXCEPTION,"用户ID为空");
        }
        List<Attendance> attendanceList = attendanceService.findAttendanceByUserId(attendance.getUserid());
        if(attendanceList.size() == 0){
            return ResultUtil.error(Constant.RESCODE_NOEXIST,"查询结果为空");
        }
        return ResultUtil.success(attendanceList, Constant.RESCODE_SUCCESS,attendanceList.size());
    }
    //模糊查询
    @PostMapping("/findAll")
    public ReturnResult findAll(@RequestParam String key){
        List<Attendance> attendanceList = attendanceService.findAll(key);
        if(attendanceList.size() == 0){
            return ResultUtil.error(Constant.RESCODE_NOEXIST,"查询结果为空");
        }
        return ResultUtil.success(attendanceList,Constant.RESCODE_SUCCESS,attendanceList.size());
    }

    @PostMapping("/updata")
    public ReturnResult updataAttendance(@RequestBody Attendance attendance){
        if(attendance.getAttendanceid() == null || attendance.getUserid() == null){
            return ResultUtil.error(Constant.RESCODE_EXCEPTION,"考勤ID或用户ID为空");
        }
        int count = attendanceService.updataAttendance(attendance);
        if(count == 0){
            return ResultUtil.error(Constant.RESCODE_MODIFYERROR,"修改失败");
        }
        return ResultUtil.success(attendance,Constant.RESCODE_SUCCESS,count);
    }

    @PostMapping("/del")
    public ReturnResult delAttendance(String [] list){
        if (list.length == 0){
            return ResultUtil.error(Constant.RESCODE_EXCEPTION,"没有需要删除的数据");
        }
        List<Attendance> attendanceList = attendanceService.delAttendance(list);
        if(attendanceList.size() == 0){
            return ResultUtil.error(Constant.RESCODE_DELETEERROR,"删除失败");
        }
        return ResultUtil.success(attendanceList, Constant.RESCODE_SUCCESS,attendanceList.size());
    }

    @PostMapping("/add")
    public ReturnResult addAttendance(@RequestBody Attendance attendance){
        if(!isNumeric(attendance.getUserid())){
            return ResultUtil.error(Constant.RESCODE_INSERTERROR,"用户ID只允许数字，添加失败");
        }
        attendance = attendanceService.addAttendance(attendance);
        if(attendance == null){
            return ResultUtil.error(Constant.RESCODE_INSERTERROR,"无效数据或该用户当天考勤数据已存在");
        }
        return ResultUtil.success(attendance, Constant.RESCODE_SUCCESS,1);
    }
    /*
     * @Author 李章
     * @Description 判断是否为数字
     * @Date 16:32 2021/4/17
     * @Param [str]
     * @return boolean
     **/
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("-?[0-9]+.?[0-9]+");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }
}
