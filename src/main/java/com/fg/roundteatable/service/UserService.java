package com.fg.roundteatable.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fg.roundteatable.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author FG
 * @since 2022-01-16
 */
public interface UserService extends IService<User> {

    User getByUsername(String username);

    IPage<User> selectPage(Page<User> pageParam, User user);
}
