package com.fg.roundteatable.util;

import io.jsonwebtoken.*;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * @description:
 * @author: fenggi123
 * @create: 8/24/2021 10:22 AM
 */
public class JwtUtils {
    private final static long EXPIRE = 1000 * 60 * 60 * 24;
    public static final String APP_SECRET = "ukc8BDbRigUDaY6pZFfWus2jZWLPHO"; //秘钥


    private static Key getKey() {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] bytes = DatatypeConverter.parseBase64Binary(APP_SECRET);
        return new SecretKeySpec(bytes, signatureAlgorithm.getJcaName());
    }

    public static String genToken(JwtInfo jwtInfo) {
        JwtBuilder builder = Jwts.builder();

        //设置Jwt header
        builder.setHeaderParam("alg", "H256");//签名算法
        builder.setHeaderParam("typ", "JWT");//令牌类型

        //设置有效载荷 payload
        builder.setSubject("user");
        builder.setIssuedAt(new Date());
        builder.setExpiration(new Date(System.currentTimeMillis() + EXPIRE));//过期时间

        //私有字段
        builder.claim("id", jwtInfo.getId());
        builder.claim("username", jwtInfo.getUsername());
        builder.claim("avatar", jwtInfo.getAvatar());

        //签名哈希
        builder.signWith(SignatureAlgorithm.HS256, getKey());
        String token = builder.compact();

        return token;
    }

    public static boolean checkToken(String token) {
        if (StringUtils.isBlank(token)) {
            return false;
        }
        try {
            Jwts.parser().setSigningKey(getKey()).parseClaimsJws(token);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean checkToken(HttpServletRequest request) {
        try {
            String jwtToken = request.getHeader("token");
            if (StringUtils.isBlank(jwtToken)) return false;
            Jwts.parser().setSigningKey(getKey()).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 根据token字符串获取用户id
     *
     * @param request
     * @return
     */
    public static String getIdByJwtToken(HttpServletRequest request) {
        String jwtToken = request.getHeader("token");
        if (StringUtils.isBlank(jwtToken)) return null;
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(getKey()).parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();
        return (String) claims.get("id");
    }


}
