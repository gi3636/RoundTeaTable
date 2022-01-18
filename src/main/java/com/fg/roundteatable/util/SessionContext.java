package com.fg.roundteatable.util;

import com.alibaba.fastjson.JSONObject;
import com.fg.roundteatable.common.GlobalException.GlobalException;
import com.fg.roundteatable.common.ResultCode.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * @description:
 * @author: fenggi123
 * @create: 8/26/2021 10:31 AM
 */
@Slf4j
@Component
public class SessionContext {

    @Autowired
    RedisUtils redisUtils;

    public final static String USER_ID_KEY = "uid";

    public JwtInfo getJwtInfo() {
        String id = getUserId();
        Object obj = redisUtils.get(RedisKeyEnum.OAUTH_APP_TOKEN.keyBuilder(id));
        if (obj == null) {
            throw GlobalException.from(ResultCode.AUTH_FAIL);
        }
        JwtInfo jwtInfo = JSONObject.parseObject(obj.toString(), JwtInfo.class);
        if (jwtInfo == null) {
            throw GlobalException.from(ResultCode.AUTH_FAIL);
        }
        return jwtInfo;
    }

    public String getUserId() {
        String id = RequestContextHolder.getRequestAttributes().getAttribute(USER_ID_KEY, RequestAttributes.SCOPE_REQUEST).toString();
        if (null == id) {
            log.error("=== 请重新登入 ===");
            throw GlobalException.from(ResultCode.AUTH_FAIL);
        }
        return id;
    }


}
