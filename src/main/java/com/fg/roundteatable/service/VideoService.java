package com.fg.roundteatable.service;

import com.fg.roundteatable.entity.Video;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 短视频表 服务类
 * </p>
 *
 * @author FG
 * @since 2022-01-16
 */
public interface VideoService extends IService<Video> {

    public Video getVideoByVideoId(String videoId);
}
