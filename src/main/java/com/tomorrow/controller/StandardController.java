package com.tomorrow.controller;

import com.tomorrow.entity.Salary;
import com.tomorrow.entity.Standard;
import com.tomorrow.service.imp.StandardServiceImp;
import com.tomorrow.util.Constant;
import com.tomorrow.util.ResultUtil;
import com.tomorrow.vo.ReturnResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/PayRollSystem/Standard")
public class StandardController {
    @Autowired
    private  StandardServiceImp standardServiceImp;

    @PostMapping(value = "/findAll")
    @ResponseBody
    public ReturnResult findAll(){
        Standard standard = standardServiceImp.findAllStandard();
        return ResultUtil.success(standard, Constant.RESCODE_SUCCESS,1);
    }

    @PostMapping(value = "/update")
    @ResponseBody
    public ReturnResult update(@RequestBody Standard standard , @RequestHeader(value = "token") String token){
        if(standard == null){
            return ResultUtil.error(Constant.RESCODE_EXCEPTION,"信息格式错误");
        }
        boolean flag = standardServiceImp.updateStandard(standard , token);
        if(flag == false){
            return ResultUtil.error(Constant.RESCODE_NOEXIST , "更新结果为空");
        }else {
            return ResultUtil.success("更新成功",Constant.RESCODE_SUCCESS,1);
        }
    }
}
