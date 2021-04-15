package com.tomorrow.controller;

import com.tomorrow.entity.User;
import com.tomorrow.service.UserService;
import com.tomorrow.util.Constant;
import com.tomorrow.util.ResultUtil;
import com.tomorrow.vo.LoginResultVo;
import com.tomorrow.vo.ReturnResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/PayrollSystem/User")
public class UserController {
    @Autowired
    private UserService userService;
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

    @RequestMapping("/")
    public String index(){
        return "page/department";
    }

}
