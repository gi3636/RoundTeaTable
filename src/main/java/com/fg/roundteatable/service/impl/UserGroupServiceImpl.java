package com.fg.roundteatable.service.impl;

import com.fg.roundteatable.entity.UserGroup;
import com.fg.roundteatable.mapper.UserGroupMapper;
import com.fg.roundteatable.service.UserGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 群组用户关联表 服务实现类
 * </p>
 *
 * @author FG
 * @since 2022-01-16
 */
@Service
public class UserGroupServiceImpl extends ServiceImpl<UserGroupMapper, UserGroup> implements UserGroupService {

}
