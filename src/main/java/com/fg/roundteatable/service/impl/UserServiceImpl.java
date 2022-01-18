package com.fg.roundteatable.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fg.roundteatable.entity.User;
import com.fg.roundteatable.mapper.UserMapper;
import com.fg.roundteatable.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author FG
 * @since 2022-01-16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User getByUsername(String username) {
        QueryWrapper <User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        User user = userMapper.selectOne(queryWrapper);
        return user;
    }
}
