package com.tomorrow.service.imp;

import com.tomorrow.entity.User;
import com.tomorrow.service.TokenService;
import com.tomorrow.util.Constant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenServiceImp implements TokenService {
    /**
     * 生成JWT
     * @param user
     * @return
     */
    @Override
    public String geneToken(User user){
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
                .signWith(SignatureAlgorithm.HS256, Constant.JWT_SECRET) //设置签名使用的签名算法和签名使用的密钥
                .compact(); //生成xxxx.xxxx.xxx样式
        return token;
    }

    /**
     * 解密token
     * @return token
     */
    @Override
    public Claims parseToken(String token){
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

}
