package com.tomorrow.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tomorrow.entity.Salary;
import com.tomorrow.service.imp.SalaryServiceImp;
import com.tomorrow.util.Constant;
import com.tomorrow.util.ResultUtil;
import com.tomorrow.vo.ReturnResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/PayRollSystem/Salary")
public class SalaryController {

    @Autowired
    private SalaryServiceImp salaryServiceImp;

    @PostMapping(value = "/findAll")
    @ResponseBody
    public ReturnResult findAllSalary(){
        List<Salary> salarys = salaryServiceImp.findAllSalary();
        return ResultUtil.success(salarys, Constant.RESCODE_SUCCESS,salarys.size());
    }

    @PostMapping(value = "/findSalaryById")
    @ResponseBody
    public ReturnResult findAllSalary(@RequestParam String salaryId){
        Salary salary = salaryServiceImp.findSalaryById(salaryId);
        return ResultUtil.success(salary, Constant.RESCODE_SUCCESS,1);
    }

    @PostMapping(value = "/showApplication")
    @ResponseBody
    public ReturnResult showApplication(@RequestHeader String token){
        if(token == null){
            return ResultUtil.error(Constant.RESCODE_EXCEPTION,"无权限");
        }
        List<Salary> salaryList = salaryServiceImp.showApplication(token);
        if(salaryList == null){
            return ResultUtil.error(Constant.RESCODE_NOEXIST,"结果为空");
        }
        return ResultUtil.success(salaryList,Constant.RESCODE_SUCCESS,salaryList.size());
    }

    @GetMapping(value = "/findSalaryByOne")
    @ResponseBody
    public ReturnResult findAllSalary(@RequestParam(value = "key") String key , @RequestParam(value = "value") String value){
        List<Salary> salary = salaryServiceImp.findSalaryByOne(key , value);
        return ResultUtil.success(salary, Constant.RESCODE_SUCCESS,salary.size());
    }

    @PostMapping(value = "/showSalary")
    @ResponseBody
    public ReturnResult showSalary(@RequestParam String token , @RequestParam String userId){
        List<Salary> salaryList = salaryServiceImp.showSalary(token,userId);
        if(salaryList == null){
            return ResultUtil.error(Constant.RESCODE_NOEXIST , "查询结果为空");
        }else {
            return ResultUtil.success(salaryList,Constant.RESCODE_SUCCESS,salaryList.size());
        }
    }

    @GetMapping(value = "/updateSalaryStatus")
    @ResponseBody
    public ReturnResult updateByOne(@RequestParam String salaryStatus , @RequestParam String userid){
        boolean flag = salaryServiceImp.updateSalaryStatus(salaryStatus,userid);
        if(flag == false){
            return ResultUtil.error(Constant.RESCODE_NOEXIST,"更新失败");
        }else {
            return ResultUtil.success("更新成功",Constant.RESCODE_SUCCESS,1);
        }
    }

    @PostMapping(value = "/delete")
    @ResponseBody
    public ReturnResult deleteSalaryById(@RequestParam String salaryId){
        if(salaryId == null){
            return ResultUtil.error(Constant.RESCODE_EXCEPTION,"ID格式错误");
        }
        boolean flag = salaryServiceImp.deleteSalary(salaryId);
        if(flag == false){
            return ResultUtil.error(Constant.RESCODE_NOEXIST,"删除失败");
        }else{
            return ResultUtil.success("删除成功",Constant.RESCODE_SUCCESS,1);
        }
    }

    @PostMapping(value = "/count")
    @ResponseBody
    public ReturnResult countByUserId(@RequestParam String userId , @RequestParam double commission , @RequestParam double bonus){
        if(userId == null){
            return ResultUtil.error(Constant.RESCODE_EXCEPTION , "userid不能为空");
        }
        boolean flag = salaryServiceImp.countSalary(userId, commission, bonus);
        if(flag == false){
            return ResultUtil.error(Constant.RESCODE_NOEXIST , "薪资统计修改失败");
        }else {
            return ResultUtil.success("薪资统计更新成功" , Constant.RESCODE_SUCCESS , 1);
        }
    }

    @PostMapping(value = "/add")
    @ResponseBody
    public ReturnResult addSalary(@RequestBody Salary salary){
        if(salary == null){
            return ResultUtil.error(Constant.RESCODE_EXCEPTION,"格式错误");
        }
        boolean flag = salaryServiceImp.addSalary(salary);
        if(flag == false){
            return ResultUtil.error(Constant.RESCODE_INSERTERROR,"增添失败");
        }else {
            return ResultUtil.success("增添成功",Constant.RESCODE_SUCCESS,1);
        }
    }

    @PostMapping(value = "/update")
    @ResponseBody
    public ReturnResult updateSalary(@RequestBody Salary salary , @RequestHeader String token){
        if(salary == null){
            return ResultUtil.error(Constant.RESCODE_EXCEPTION,"格式错误");
        }
        if(salary.getUserid() == null){
            return ResultUtil.error(Constant.RESCODE_EXCEPTION,"id值不能为空");
        }
        boolean flag = salaryServiceImp.updateSalary(salary , token);
        if(flag == false){
            return ResultUtil.error(Constant.RESCODE_EXCEPTION,"修改失败");
        }else {
            return ResultUtil.success("修改成功",Constant.RESCODE_SUCCESS,1);
        }
    }

    @PostMapping(value = "/findByDe")
    @ResponseBody
    public ReturnResult findSalaryByDep(@RequestParam String department){
        if(department == null){
            return ResultUtil.error(Constant.RESCODE_EXCEPTION,"格式错误");
        }
        List<Salary> salaryList = salaryServiceImp.findSalaryByDep(department);
        if(salaryList.size() == 0){
            return ResultUtil.error(Constant.RESCODE_NOEXIST,"查询结果为空");
        }else {
            return ResultUtil.success(salaryList,Constant.RESCODE_SUCCESS,salaryList.size());
        }
    }
}
