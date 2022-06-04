package com.fg.roundteatable.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fg.roundteatable.entity.Video;
import com.fg.roundteatable.mapper.VideoMapper;
import com.fg.roundteatable.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 短视频表 服务实现类
 * </p>
 *
 * @author FG
 * @since 2022-01-16
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

    @Autowired
    VideoMapper videoMapper;

    @Override
    public Video getVideoByVideoId(String videoId) {
        QueryWrapper<Video> videoQueryWrapper  = new QueryWrapper<>();
        videoQueryWrapper.eq("video_id",videoId);
        Video video = videoMapper.selectOne(videoQueryWrapper);
        return video;
    }

    @Override
    public List<Video> getVideoListByUserId(String userId) {
        QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("user_id",userId);
        videoQueryWrapper.orderByAsc("created_time");
        List<Video> videoList = videoMapper.selectList(videoQueryWrapper);
        return videoList;
    }


}
