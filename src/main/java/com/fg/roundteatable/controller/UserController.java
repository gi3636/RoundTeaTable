package com.fg.roundteatable.controller;


import com.fg.roundteatable.common.ResultVo;
import com.fg.roundteatable.entity.User;
import com.fg.roundteatable.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author FG
 * @since 2022-01-16
 */
@RestController
@Slf4j
@RequestMapping("/user")

public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation("获取用户")
    @GetMapping("{id}")
    public ResultVo getUserData(@PathVariable String id){
        User user = userService.getById(id);
        return ResultVo.ok().data("user",user);
    }


}

