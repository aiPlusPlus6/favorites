package com.aiplusplus.favorites.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * @author 李俊杰
 * {@code @date} 2024年02月28日 9:32
 * @package com.aiplusplus.favorites.common.jwt
 * @ClassName: JwtUtil
 * @Description: TODO(token生成校验类)
 */
@Component
@Slf4j
public class JwtUtil {
    //常量
    public static final long EXPIRE = 1000 * 60 * 60 * 4; //token过期时间,4个小时
    public static final String APP_SECRET = "ukc8BDbRigUDaY6pZFfWus2jZWLPHO"; //秘钥
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    //生成token
    public  String getToken(String userName){
        String token = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .setSubject("user")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .claim("userName", userName)//设置token主体部分 ，存储用户信息
                .signWith(SignatureAlgorithm.HS256, APP_SECRET)
                .compact();
        //删除旧token
        redisTemplate.delete(userName);
        log.info("token:{}",token);
        //将token存入redis过期时间为4小时
        redisTemplate.opsForValue().set(userName, token, 4 * 60 * 60);
        return token;
    }

    //验证token字符串是否是有效的  包括验证是否过期
    public boolean checkToken(String jwtToken) {
        if(jwtToken == null || jwtToken.isEmpty()){
            log.error("Jwt is empty");
            return false;
        }
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
            Claims body = claims.getBody();
            //从token取出用户名
            String userName = (String) body.get("userName");
            //从redis中取出token
            String token = redisTemplate.opsForValue().get(userName);
            //去除token里的 空字段
            if(token!=null){
                token = token.trim();
            }
            if (token == null || !token.equals(jwtToken)){
                return false;
            }
            if ( body.getExpiration().after(new Date(System.currentTimeMillis()))){
                return true;
            } else
                return false;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    public Claims getTokenBody(String jwtToken){
        if(jwtToken == null || jwtToken.isEmpty()){
            log.error("Jwt is empty");
            return null;
        }
        try {
            return Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken).getBody();
        } catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    //删除redis中的token
    public void deleteToken(String userName){
        redisTemplate.delete(userName);
    }
}
