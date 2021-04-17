package com.tomorrow.controller;

import com.tomorrow.entity.User;
import com.tomorrow.service.CheckService;
import com.tomorrow.service.UserService;
import com.tomorrow.util.Constant;
import com.tomorrow.util.ResultUtil;
import com.tomorrow.vo.LoginResultVo;
import com.tomorrow.vo.ReturnResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/PayrollSystem/User")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private CheckService checkService;
    @ApiOperation(value = "用户登录接口")
    @ApiImplicitParam()
    @PostMapping("/login")
    public ReturnResult login(@RequestBody User user){
        String token = userService.login(user);
        if(token == null){
            return ResultUtil.error(Constant.JWT_ERRCODE_FAIL,"密码/账号错误");
        }
        user = userService.findUserById(user.getUserId()).setPassword("******");
        LoginResultVo loginResultVo = new LoginResultVo().setUser(user).setToken(token);
        return ResultUtil.success(loginResultVo,Constant.RESCODE_SUCCESS,1);
    }
    /**
     * 庞海
     * 获取用户所有权限
     * @param userId
     * @param token
     * @return
     */
    @GetMapping("/getAllPower")
    public ReturnResult getUserAllPower(@RequestParam String userId, @RequestParam String token){
        //验证token
        int res = checkService.checkToken(userId,token);
        if(Constant.JWT_ERRCODE_EXPIRE==res){
            return ResultUtil.error(Constant.JWT_ERRCODE_EXPIRE,"token已过期");
        }else if(Constant.JWT_ERRCODE_FAIL==res){
            return ResultUtil.error(Constant.JWT_ERRCODE_FAIL,"验证不通过");
        }
        //验证是否有权操作
        if(checkService.checkPower(userId,Constant.PRI_PERMISION)==Constant.RESCODE_NOAUTH){
            return ResultUtil.error(Constant.RESCODE_NOAUTH,"无权操作");
        }
        //查询所有人权限
        List<User> list = userService.findAllUserPower();
        return ResultUtil.success(list,Constant.RESCODE_SUCCESS,list.size());
    }

    /**
     * 庞海
     * 更新权限
     * @param userId
     * @param token
     * @param power
     * @return
     */
    @GetMapping("/updateUserPower")
    public ReturnResult updateUserPower(@RequestParam String userId, @RequestParam String token, @RequestParam String power){
        //验证token
        int res = checkService.checkToken(userId,token);
        if(Constant.JWT_ERRCODE_EXPIRE==res){
            return ResultUtil.error(Constant.JWT_ERRCODE_EXPIRE,"token已过期");
        }else if(Constant.JWT_ERRCODE_FAIL==res){
            return ResultUtil.error(Constant.JWT_ERRCODE_FAIL,"验证不通过");
        }
        //验证是否有权操作
        if(checkService.checkPower(userId,Constant.PRI_PERMISION)==Constant.RESCODE_NOAUTH){
            return ResultUtil.error(Constant.RESCODE_NOAUTH,"无权操作");
        }
        //修改权限
        if(userService.updateUserPower(userId,power)!=0){
            return ResultUtil.success("修改成功",Constant.RESCODE_SUCCESS,1);
        }else{
            return ResultUtil.error(Constant.RESCODE_MODIFYERROR,"修改失败");
        }
    }
}
