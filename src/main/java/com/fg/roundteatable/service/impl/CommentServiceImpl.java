package com.fg.roundteatable.service.impl;

import com.fg.roundteatable.entity.Comment;
import com.fg.roundteatable.mapper.CommentMapper;
import com.fg.roundteatable.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论表 服务实现类
 * </p>
 *
 * @author FG
 * @since 2022-01-16
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
