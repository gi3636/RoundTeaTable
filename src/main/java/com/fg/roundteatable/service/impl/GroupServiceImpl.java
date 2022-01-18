package com.fg.roundteatable.service.impl;

import com.fg.roundteatable.entity.Group;
import com.fg.roundteatable.mapper.GroupMapper;
import com.fg.roundteatable.service.GroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 群聊 服务实现类
 * </p>
 *
 * @author FG
 * @since 2022-01-16
 */
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, Group> implements GroupService {

}
