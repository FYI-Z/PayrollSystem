package com.tomorrow.service.imp;

import com.tomorrow.entity.User;
import com.tomorrow.service.CheckService;
import com.tomorrow.service.RedisService;
import com.tomorrow.service.TokenService;
import com.tomorrow.service.UserService;
import com.tomorrow.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckServiceImp implements CheckService {

    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private RedisService redisService;
    /**
     * 验证token的合法性
     * @param useridByFront
     * @param token
     * @return
     */
    @Override
    public int checkToken(String useridByFront, String token) {
        String useridByToken = (String) tokenService.parseToken(token).get("userId");
        //传进的userid与解析出的userid不等
        if(!useridByFront.equals(useridByToken)){
            return Constant.JWT_ERRCODE_FAIL;
        }
        String value = redisService.getString(useridByToken);
        if(value==null){
            return Constant.REDIS_EXPIRE_TIME;//已过期
        }
        //刷新token的生存时间
        redisService.flushExpire(useridByFront,Constant.REDIS_EXPIRE_TIME);
        return Constant.JWT_RESCODE_SUCCESS; //验证通过
    }

    /**
     * 验证操作权限
     * @param userid
     * @param index
     * @return
     */
    @Override
    public int checkPower(String userid, int index) {
        User user = userService.findUserById(userid);
        List<String> strList = StringUtil.strToList(user.getPermission());
        if("0".equals(strList.get(index))){ //无权操作
            return Constant.RESCODE_NOAUTH;
        }else{
            return 1;
        }
    }

}
