package com.fg.roundteatable.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fg.roundteatable.common.ResultVo;
import com.fg.roundteatable.common.ValidException.UpdateGroup;
import com.fg.roundteatable.entity.User;
import com.fg.roundteatable.service.UserService;
import com.fg.roundteatable.util.MD5;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation("添加测试用户")
    @GetMapping("/addUsers")
    public void addUsersData() {
        for (int i = 0; i < 50; i++) {
            User user = new User();
            user.setUsername("用户"+i);
            user.setPassword(MD5.encrypt("123456"));
            userService.save(user);
        }
    }
    @ApiOperation("测试数据")
    @GetMapping("/testData")
    public Object getPage(){
        //  分页查询对象，未添加任何分页参数
        Page<User> page=new Page();
        Page<User> userPage = userService.page(page);
        System.err.println("查询结果列表大小:"+userPage.getRecords().size());
        System.err.println("总条数:"+userPage.getTotal());
        System.err.println("countId:"+userPage.getCountId());
        System.err.println("当前页:"+userPage.getCurrent());
        System.err.println("每页显示条数，默认 10:"+userPage.getSize());
        System.err.println("最大分页条数限制:"+userPage.getMaxLimit());
        System.err.println("总页数:"+userPage.getPages());
        return userPage;
    }

    @ApiOperation("获取用户")
    @GetMapping("{id}")
    public ResultVo getUserData(@PathVariable String id) {
        User user = userService.getById(id);
        return ResultVo.ok().data("user", user);
    }

    @ApiOperation("更新用户资料")
    @PostMapping("/update")
    public ResultVo updateUser(@Validated(UpdateGroup.class) @RequestBody  User user) {
        boolean result = userService.updateById(user);
        if (result) {
            return ResultVo.ok().msg("修改成功");
        } else {
            return ResultVo.error().msg("数据不存在");
        }
    }

    @ApiOperation("删除用户")
    @DeleteMapping("/remove/{id}")
    public ResultVo deleteUser(@PathVariable String id) {
        boolean result = userService.removeById(id);
        if (result) {
            return ResultVo.ok().msg("删除成功");
        } else {
            return ResultVo.error().msg("数据不存在");
        }
    }

    @ApiOperation("模糊查询用户")
    @GetMapping("list/{key}")
    public ResultVo getUserByKey(@PathVariable String key) {
        return ResultVo.ok();
    }

    @ApiOperation("用户分页查询")
    @GetMapping("list/{page}/{limit}")
    public ResultVo listPage(@ApiParam(value = "当前页面") @PathVariable Integer page,
                             @ApiParam(value = "每页记录数") @PathVariable Integer limit,
                             @ApiParam("列表查询对象") User user) {
        Page<User> pageParam = new Page<>(page,limit);
        IPage<User> pageModel =userService.selectPage(pageParam,user);
        pageParam.getTotal();
        pageParam.getPages();
        return null;
    }


}

