package com.fg.roundteatable.service;

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

}
