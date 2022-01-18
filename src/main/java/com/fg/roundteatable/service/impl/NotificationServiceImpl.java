package com.fg.roundteatable.service.impl;

import com.fg.roundteatable.entity.Notification;
import com.fg.roundteatable.mapper.NotificationMapper;
import com.fg.roundteatable.service.NotificationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 消息通知 服务实现类
 * </p>
 *
 * @author FG
 * @since 2022-01-16
 */
@Service
public class NotificationServiceImpl extends ServiceImpl<NotificationMapper, Notification> implements NotificationService {

}
