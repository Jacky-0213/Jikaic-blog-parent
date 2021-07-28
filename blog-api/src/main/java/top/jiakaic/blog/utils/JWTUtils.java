package top.jiakaic.blog.utils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import top.jiakaic.blog.vo.ErrorCode;
import top.jiakaic.blog.vo.Result;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author JK
 * @date 2021/7/26 -20:12
 * @Description
 **/
public class JWTUtils {

    private static final String jwtToken = "Jiakaic0213!@#$$";

    public static String createToken(Long userId){
        Map<String,Object> claims = new HashMap<>();
        claims.put("userId",userId);
        JwtBuilder jwtBuilder = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, jwtToken) // 签发算法，秘钥为jwtToken
                .setClaims(claims) // body数据，要唯一，自行设置
                .setIssuedAt(new Date()) // 设置签发时间
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 60 * 1000));// 一天的有效时间
        String token = jwtBuilder.compact();
        return token;
    }

    public static Map<String, Object> checkToken(String token){
        try {
            if(StringUtils.isBlank(token)){
                return null;
            }
            Jwt parse = Jwts.parser().setSigningKey(jwtToken).parse(token);
            return (Map<String, Object>) parse.getBody();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String token = createToken(123L);
        System.out.println(token);
        Map<String, Object> stringObjectMap = checkToken(token);
        System.out.println(stringObjectMap);

    }

}