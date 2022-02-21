package com.fg.roundteatable.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fg.roundteatable.entity.User;
import com.fg.roundteatable.mapper.UserMapper;
import com.fg.roundteatable.service.UserService;
import org.apache.commons.lang3.StringUtils;
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
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = userMapper.selectOne(queryWrapper);
        return user;
    }

    @Override
    public IPage<User> selectPage(Page<User> pageParam, User user) {
        //1.排序：按照时间字段排序
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("createdTime");

        //2.分页查询，空是无条件
        if (user == null) {
            return baseMapper.selectPage(pageParam, queryWrapper);
        }

        //3.条件查询
        String username = user.getUsername();
        String nickname = user.getNickname();
        Integer sex = user.getSex();
        String city = user.getCity();
        String country = user.getCountry();
        String birthday = user.getBirthday();
        String mobile = user.getMobile();
        String updatedTime = user.getUpdatedTime().toString();
        String createdTime = user.getCreatedTime().toString();

        if (!StringUtils.isBlank(username)) {
            queryWrapper.likeRight("username", username);
        }
        if (!StringUtils.isBlank(nickname)) {
            queryWrapper.likeRight("nickname", nickname);
        }
        if (sex != null) {
            queryWrapper.eq("sex", sex);
        }
        if (!StringUtils.isBlank(country)) {
            queryWrapper.eq("country", country);
        }
        if (!StringUtils.isBlank(city)) {
            queryWrapper.eq("city", city);
        }
        if(!StringUtils.isBlank(birthday)){
            queryWrapper.gt("birthday",birthday);
        }
        if (!StringUtils.isBlank(mobile)){
            queryWrapper.eq("mobile",mobile);
        }

        if (!StringUtils.isBlank(updatedTime)){
            queryWrapper.gt("updatedTime",updatedTime);
        }
        if (!StringUtils.isBlank(createdTime)){
            queryWrapper.gt("createdTime",createdTime);
        }
        return baseMapper.selectPage(pageParam,queryWrapper);
    }
}
