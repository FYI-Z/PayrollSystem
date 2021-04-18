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

    /**
     * 庞海
     * 用户登录
     */
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
     * 获取所有用户的基本信息
     * @param userId
     * @param token
     * @return
     */
    @GetMapping("/getAllUserInfo")
    public ReturnResult getAllUserInfo(@RequestParam String userId,  @RequestParam String token){
        //验证token
        int res = checkService.checkToken(userId,token);
        if(Constant.JWT_ERRCODE_EXPIRE==res){
            return ResultUtil.error(Constant.JWT_ERRCODE_EXPIRE,"token已过期");
        }else if(Constant.JWT_ERRCODE_FAIL==res){
            return ResultUtil.error(Constant.JWT_ERRCODE_FAIL,"验证不通过");
        }
        //验证是否有权操作
        if(checkService.checkPower(userId,Constant.PRI_INFOLIET)==Constant.RESCODE_NOAUTH){
            return ResultUtil.error(Constant.RESCODE_NOAUTH,"无权操作");
        }
        //查询所有人的信息
        List<User> list = userService.findAllUserInfo();
        return ResultUtil.success(list,Constant.RESCODE_SUCCESS,list.size());
    }

    /**
     * 庞海
     * 创建用户
     * @param userId
     * @param token
     * @return
     */
    @GetMapping("/createUser")
    public ReturnResult createUser(@RequestParam String userId,  @RequestParam String token){
        //验证token
        int res = checkService.checkToken(userId,token);
        if(Constant.JWT_ERRCODE_EXPIRE==res){
            return ResultUtil.error(Constant.JWT_ERRCODE_EXPIRE,"token已过期");
        }else if(Constant.JWT_ERRCODE_FAIL==res){
            return ResultUtil.error(Constant.JWT_ERRCODE_FAIL,"验证不通过");
        }
        //验证是否有权操作
        if(checkService.checkPower(userId,Constant.PRI_INFOLIET)==Constant.RESCODE_NOAUTH){
            return ResultUtil.error(Constant.RESCODE_NOAUTH,"无权操作");
        }
        //创建用户
        if (userService.addUser()==0){
            return ResultUtil.error(Constant.RESCODE_INSERTERROR,"创建用户失败");
        }else{
            return ResultUtil.success("创建用户成功", Constant.RESCODE_SUCCESS,1);
        }
    }

    /**
     * 庞海
     * 删除用户
     * @param userId
     * @param token
     * @return
     */
    @GetMapping("/delUser")
    public ReturnResult delUser(@RequestParam String userId,  @RequestParam String token,@RequestParam String id){
        //验证token
        int res = checkService.checkToken(userId,token);
        if(Constant.JWT_ERRCODE_EXPIRE==res){
            return ResultUtil.error(Constant.JWT_ERRCODE_EXPIRE,"token已过期");
        }else if(Constant.JWT_ERRCODE_FAIL==res){
            return ResultUtil.error(Constant.JWT_ERRCODE_FAIL,"验证不通过");
        }
        //验证是否有权操作
        if(checkService.checkPower(userId,Constant.PRI_INFOLIET)==Constant.RESCODE_NOAUTH){
            return ResultUtil.error(Constant.RESCODE_NOAUTH,"无权操作");
        }
        //删除用户
        if (userService.delUser(id)==0){
            return ResultUtil.error(Constant.RESCODE_DELETEERROR,"删除用户失败");
        }else{
            return ResultUtil.success("删除用户成功", Constant.RESCODE_SUCCESS,1);
        }
    }

    /**
     * 庞海
     * 更新用户状态
     * @param userId
     * @param token
     * @param id
     * @return
     */
    @GetMapping("/updateUserStatus")
    public ReturnResult updateUserStatus(@RequestParam String userId, @RequestParam String token, @RequestParam String id,@RequestParam int status){
        //验证token
        int res = checkService.checkToken(userId,token);
        if(Constant.JWT_ERRCODE_EXPIRE==res){
            return ResultUtil.error(Constant.JWT_ERRCODE_EXPIRE,"token已过期");
        }else if(Constant.JWT_ERRCODE_FAIL==res){
            return ResultUtil.error(Constant.JWT_ERRCODE_FAIL,"验证不通过");
        }
        //验证是否有权操作
        if(checkService.checkPower(userId,Constant.PRI_INFOLIET)==Constant.RESCODE_NOAUTH){
            return ResultUtil.error(Constant.RESCODE_NOAUTH,"无权操作");
        }
        //更新用户状态
        if(userService.updateUserStatus(id,status)==0){
            return ResultUtil.error(Constant.RESCODE_MODIFYERROR,"更新用户状态失败");
        }else{
            return ResultUtil.success("更新用户状态成功",Constant.RESCODE_SUCCESS,1);
        }
    }

    /**
     * 庞海
     * 将用户关联至某个部门
     * @param userId
     * @param token
     * @param id
     * @param department
     * @return
     */
    @GetMapping("/conUserToDepart")
    public ReturnResult conUserToDepart(@RequestParam String userId,  @RequestParam String token, @RequestParam String id, @RequestParam String department){
        //验证token
        int res = checkService.checkToken(userId,token);
        if(Constant.JWT_ERRCODE_EXPIRE==res){
            return ResultUtil.error(Constant.JWT_ERRCODE_EXPIRE,"token已过期");
        }else if(Constant.JWT_ERRCODE_FAIL==res){
            return ResultUtil.error(Constant.JWT_ERRCODE_FAIL,"验证不通过");
        }
        //验证是否有权操作
        if(checkService.checkPower(userId,Constant.PRI_INFOLIET)==Constant.RESCODE_NOAUTH){
            return ResultUtil.error(Constant.RESCODE_NOAUTH,"无权操作");
        }
        //启用用户
        if(userService.updateUserDepart(id,department)==0){
            return ResultUtil.error(Constant.RESCODE_MODIFYERROR,"关联部门失败");
        }else{
            return ResultUtil.success("关联部门成功",Constant.RESCODE_SUCCESS,1);
        }
    }

    /**
     * 庞海
     * 获取用户所有权限
     * @param userId
     * @param token
     * @return
     */
    @GetMapping("/getAllPower")
    public ReturnResult getUserAllPower(@RequestParam String userId,  @RequestParam String token){
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
     * @param id
     * @param token
     * @param power
     * @return
     */
    @GetMapping("/updateUserPower")
    public ReturnResult updateUserPower(@RequestParam String userId, @RequestParam String id,  @RequestParam String token, @RequestParam String power){
        //验证token
        int res;
        try{
            res = checkService.checkToken(userId,token);
        }catch (Exception e){
            e.printStackTrace();
            res = Constant.JWT_ERRCODE_FAIL;
        }

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
        if(userService.updateUserPower(id,power)!=0){
            return ResultUtil.success("修改成功",Constant.RESCODE_SUCCESS,1);
        }else{
            return ResultUtil.error(Constant.RESCODE_MODIFYERROR,"修改失败");
        }
    }

    /**
     * 庞海
     * 搜索用户
     * @param userId
     * @param id
     * @param token
     * @param power
     * @return
     */
    @GetMapping("/searchPower")
    public ReturnResult searchUserPower(@RequestParam String userId, @RequestParam String id,  @RequestParam String token, @RequestParam String power){
        //验证token
        int res;
        try{
            res = checkService.checkToken(userId,token);
        }catch (Exception e){
            e.printStackTrace();
            res = Constant.JWT_ERRCODE_FAIL;
        }

        if(Constant.JWT_ERRCODE_EXPIRE==res){
            return ResultUtil.error(Constant.JWT_ERRCODE_EXPIRE,"token已过期");
        }else if(Constant.JWT_ERRCODE_FAIL==res){
            return ResultUtil.error(Constant.JWT_ERRCODE_FAIL,"验证不通过");
        }
        //验证是否有权操作
        if(checkService.checkPower(userId,Constant.PRI_PERMISION)==Constant.RESCODE_NOAUTH){
            return ResultUtil.error(Constant.RESCODE_NOAUTH,"无权操作");
        }
        //搜索
        List<User> list = userService.findUserPower(id);
        return ResultUtil.success(list,Constant.RESCODE_SUCCESS,list.size());
    }

    @PostMapping(value = "/findUserById",produces = "application/json;charset=UTF-8")
    public ReturnResult findUserById(@RequestBody  User user){
        user = userService.findUserById(user.getUserId());
        if (user==null){
            return ResultUtil.error(Constant.RESCODE_INSERTERROR,"查询失败");
        }
        else {
            return  ResultUtil.success(user,Constant.RESCODE_SUCCESS,1);
        }
    }
}
