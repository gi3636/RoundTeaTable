package com.fg.roundteatable.service.impl;

import com.fg.roundteatable.entity.Message;
import com.fg.roundteatable.mapper.MessageMapper;
import com.fg.roundteatable.service.MessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 聊天信息表 服务实现类
 * </p>
 *
 * @author FG
 * @since 2022-01-16
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

}
