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
    public static final int JWT_RESCODE_SUCCESS = 1013;     //验证通过
    public static final long JWT_EXPIRE_TIME = 60*10;    //10分钟过期时间
    public static final String JWT_SECRET = "ioSjcoa21849sa"; //私钥

    /**
     * redis
     */
    public static final String REDIS_HOST = "8.136.234.32";  //主机ip
    public static final int REDIS_PORT = 6354;                //端口号
    public static final String REDIS_PASS = "Admin123";        //连接redis密码
    public static final int REDIS_EXPIRE_TIME = 60*30;  //设置TTL为30分钟
    public static final int REDIS_MAX_ACTIVE = 1024;          //最大连接数
    public static final int REDIS_MAX_IDLE = 100;             //最大空闲连接数
    public static final int REDIS_MAX_WAIT = 1000*10;        //获取可用连接的最大等待时间

    /**
     * 权限
     */
    public static final int PRI_INFOLIET = 0;    //信息列表权限
    public static final int PRI_PERMISION = 1;   //权限管理
    public static final int PRI_DEPARTMENT = 2;  //部门管理权限
    public static final int PRI_SALARY = 3;      //薪资核对权限
    public static final int PRI_PUNISH = 4;      //奖惩管理权限

    /**
     * 用户
     */
    public static final int YEAR = 2021;   //当前年份

}
