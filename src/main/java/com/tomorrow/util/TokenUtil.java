package com.tomorrow.util;

import com.tomorrow.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class TokenUtil {

    /**
     * 生成JWT
     * @param user
     * @return
     */
    public static String geneToken(User user){
        if(user==null){
            return null;
        }
        String token = Jwts.builder().setSubject(user.getUserId()) //jwt的所有者
                .claim("userId",user.getUserId())
                .claim("name",user.getName())
                .claim("sex",user.getSex())
                .claim("age",user.getSex())
                .claim("department",user.getDepartment())
                .claim("position",user.getPosition())
                .claim("phone",user.getPhone())
                .claim("permission",user.getPermission())    //声明载荷
                .setIssuedAt(new Date()) //设置jwt的签发时间
                .signWith(SignatureAlgorithm.HS256,Constant.JWT_SECRET) //设置签名使用的签名算法和签名使用的密钥
                .compact(); //生成xxxx.xxxx.xxx样式
        return token;
    }

    /**
     * 解密token
     * @return token
     */
    public static Claims parseToken(String token){
        try{
            final Claims claims = Jwts.parser()
                    .setSigningKey(Constant.JWT_SECRET)
                    .parseClaimsJws(token).getBody();
            return claims;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 校验token
     * @param useridByFront
     * @param token
     * @return
     */
    public static int checkToken(String useridByFront,String token){
        String useridByToken = (String) parseToken(token).get("userId");
        //传进的userid与解析出的userid不等
        if(!useridByFront.equals(useridByToken)){
            System.out.println(useridByFront);
            System.out.println(useridByToken);
            return 0;
        }
        String value = RedisUtil.getString(useridByToken);
        if(value==null){ //已过期
            return -1;
        }
        return 1; //验证通过
    }
}
