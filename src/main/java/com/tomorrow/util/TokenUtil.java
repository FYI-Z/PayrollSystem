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
    public String geneToken(User user){
        if(user==null){
            return null;
        }
        String token = Jwts.builder().setSubject(user.getUserId()) //jwt的所有者
                .claim("user",user) //声明载荷
                .setIssuedAt(new Date()) //设置jwt的签发时间
                .setExpiration(new Date(System.currentTimeMillis()+Constant.JWT_EXPIRE_TIME)) //设置jwt过期时间
                .signWith(SignatureAlgorithm.HS256,Constant.JWT_SECRET) //设置签名使用的签名算法和签名使用的密钥
                .compact(); //生成xxxx.xxxx.xxx样式

        return token;
    }

    /**
     * 解密token
     * @return token
     */
    public Claims parseToken(String token){
        final Claims claims = Jwts.parser()
                .setSigningKey(Constant.JWT_SECRET)
                .parseClaimsJws(token).getBody();
        return claims;
    }

}
