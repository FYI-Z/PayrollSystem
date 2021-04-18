package com.tomorrow.controller;

import com.tomorrow.entity.Record;
import com.tomorrow.service.imp.RecordServiceImp;
import com.tomorrow.service.imp.StandardServiceImp;
import com.tomorrow.util.Constant;
import com.tomorrow.util.ResultUtil;
import com.tomorrow.vo.ReturnResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/PayRollSystem/Record")
public class RecordController {
    @Autowired
    private RecordServiceImp recordServiceImp;

    @PostMapping(value = "/addRecord")
    @ResponseBody
    public ReturnResult addRecord(@RequestBody Record record){
        if(record == null){
            return ResultUtil.error(Constant.RESCODE_EXCEPTION,"请求格式错误");
        }
        if(record.getUserid() == null || !(record.getUserid() instanceof String)){
            return ResultUtil.error(Constant.RESCODE_EXCEPTION,"userid不能为空或userid不为字符串");
        }
        boolean flag = recordServiceImp.addRecord(record);
        if(flag == false){
            return ResultUtil.error(Constant.RESCODE_EXCEPTION,"添加失败");
        }else{
            return ResultUtil.success("添加成功",Constant.RESCODE_SUCCESS,1);
        }
    }

    @PostMapping(value = "/deleteRecordByRecordId")
    @ResponseBody
    public ReturnResult deleteRecordById(@RequestParam String recordId){
        if(recordId == null){
            return ResultUtil.error(Constant.RESCODE_EXCEPTION,"删除id不能为空");
        }
        boolean flag = recordServiceImp.deleteRecord(recordId);
        if(flag == false){
            return ResultUtil.error(Constant.RESCODE_NOEXIST,"删除失败");
        }else {
            return ResultUtil.success("删除成功",Constant.RESCODE_SUCCESS,1);
        }
    }

    @PostMapping(value = "/findAllRecord")
    @ResponseBody
    public ReturnResult findAllRecord(){
        List<Record> recordList = recordServiceImp.findAllRecord();
        if (recordList == null){
            return ResultUtil.error(Constant.RESCODE_NOEXIST,"查询结果为空");
        }else {
            return ResultUtil.success(recordList,Constant.JWT_ERRCODE_EXPIRE,recordList.size());
        }
    }

    @PostMapping(value = "/updateRecordByUserId")
    @ResponseBody
    public ReturnResult updateRecord(@RequestBody Record record){
        if(record == null){
            return ResultUtil.error(Constant.RESCODE_EXCEPTION,"修改值不能为空");
        }
        if (record.getUserid() == null){
            return ResultUtil.error(Constant.RESCODE_EXCEPTION,"userid不能为空");
        }
        boolean flag = recordServiceImp.updateRecordByUserId(record);
        if(flag == false){
            return ResultUtil.error(Constant.RESCODE_NOEXIST,"更新失败");
        }else {
            return ResultUtil.success("更新成功",Constant.RESCODE_SUCCESS,1);
        }
    }
}
