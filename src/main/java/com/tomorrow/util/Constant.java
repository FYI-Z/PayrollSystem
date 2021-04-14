package com.tomorrow.util;

public class Constant {

    /**
     * 数据请求返回码
     * */
    public static final int RESCODE_SUCCESS = 1000;         //成功
    public static final int RESCODE_SUCCESS_MSG = 1001;         //成功(有返回信息)
    public static final int RESCODE_EXCEPTION = 1002;         //请求抛出异常
    public static final int RESCODE_NOLOGIN = 1003;         //未登录状态
    public static final int RESCODE_NOEXIST = 1004;         //查询结果为空
    public static final int RESCODE_NOAUTH = 1005;         //无操作权限
    public static final int RESCODE_LOGINEXPIRE = 1006;         //登录过期
    public static final int RESCODE_INSERTERROR = 1007;         //插入失败
    public static final int RESCODE_MODIFYERROR= 1008;         //修改失败
    public static final int RESCODE_DELETEERROR = 1010;         //删除失败

    /**
     * token
     * */
    public static final int JWT_ERRCODE_EXPIRE = 1010;         //token过期
    public static final int JWT_ERRCODE_FAIL = 1012;         //验证不通过
    public static final long JWT_EXPIRE_TIME = 60*10;    //10分钟过期时间
    public static final String JWT_SECRET = "ioSjcoa21849sa"; //私钥
}
