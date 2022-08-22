package com.fg.roundteatable.controller;

import com.alibaba.fastjson.JSONObject;
import com.fg.roundteatable.common.GlobalException.GlobalException;
import com.fg.roundteatable.common.ResultCode.ResultCode;
import com.fg.roundteatable.common.ResultVo;
import com.fg.roundteatable.entity.User;
import com.fg.roundteatable.model.form.LoginForm;
import com.fg.roundteatable.model.form.RegisterForm;
import com.fg.roundteatable.service.UserService;
import com.fg.roundteatable.util.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api("登入与注册")
@RestController
@Slf4j
@RequestMapping("/")
public class LoginController {
    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private UserService userService;

    @ApiOperation("登入")
    @PostMapping("login")
    public ResultVo login(@RequestBody LoginForm loginForm) {
        String username = loginForm.getUsername().trim();
        String password = loginForm.getPassword().trim();
        //非空验证
        if (StringUtils.isBlank(username)
                || StringUtils.isBlank(password)) {
            throw GlobalException.from(ResultCode.LOGIN_ERROR);
        }
        User user = userService.getByUsername(username);
        if (user == null) {
            throw GlobalException.from(ResultCode.LOGIN_ERROR);
        }
        //密码验证
        if (!MD5.encrypt(password).equals(user.getPassword())) {
            throw GlobalException.from(ResultCode.LOGIN_ERROR);
        }

        //生成token
        JwtInfo jwtInfo = new JwtInfo();
        jwtInfo.setId(user.getId());
        jwtInfo.setUsername(user.getUsername());
        jwtInfo.setAvatar(user.getAvatar());
        String token = JwtUtils.genToken(jwtInfo);
        // redisUtils.set(RedisKeyEnum.OAUTH_APP_TOKEN.keyBuilder(String.valueOf(jwtInfo.getId())), JSONObject.toJSONString(jwtInfo), 60 * 60 * 24);
        redisUtils.set(RedisKeyEnum.OAUTH_APP_TOKEN.keyBuilder(String.valueOf(jwtInfo.getId())), JSONObject.toJSONString(jwtInfo), 60 * 60 * 24);
        return new ResultVo(ResultCode.SUCCESS).data("token", token).data("user",jwtInfo);
    }

    @ApiOperation("注册")
    @PostMapping("register")
    public ResultVo register(@RequestBody RegisterForm registerForm) {
        String username = registerForm.getUsername();
        String password = registerForm.getPassword();
        String confirmPassword = registerForm.getConfirmPassword();
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password) || StringUtils.isBlank(confirmPassword)) {
            throw GlobalException.from(ResultCode.REGISTER_ERROR);
        }
        if (!password.equals(confirmPassword)) {
            throw GlobalException.from(ResultCode.PASSWORD_NOT_SAME);
        }

        if (userService.getByUsername(username) != null) {
            throw GlobalException.from(ResultCode.ACCOUNT_EXIST);
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(MD5.encrypt(password));
        userService.save(user);
        return new ResultVo(ResultCode.SUCCESS);
    }
}
