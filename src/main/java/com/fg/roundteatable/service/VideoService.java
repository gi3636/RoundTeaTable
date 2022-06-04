package com.fg.roundteatable.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fg.roundteatable.entity.Video;

import java.util.List;

/**
 * <p>
 * 短视频表 服务类
 * </p>
 *
 * @author FG
 * @since 2022-01-16
 */
public interface VideoService extends IService<Video> {

     Video getVideoByVideoId(String videoId);

     List<Video> getVideoListByUserId(String userId);
}
