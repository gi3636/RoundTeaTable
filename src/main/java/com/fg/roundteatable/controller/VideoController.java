package com.fg.roundteatable.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.fg.roundteatable.common.GlobalException.GlobalException;
import com.fg.roundteatable.common.ResultCode.ResultCode;
import com.fg.roundteatable.common.ResultVo;
import com.fg.roundteatable.config.VodProperties;
import com.fg.roundteatable.entity.Video;
import com.fg.roundteatable.model.vo.StsToken;
import com.fg.roundteatable.service.VideoService;
import com.fg.roundteatable.util.AliyunVodSDKUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 * 短视频表 前端控制器
 * </p>
 *
 * @author FG
 * @since 2022-01-16
 */
@RestController
@Slf4j
@RequestMapping("/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @Autowired
    private VodProperties vodProperties;

    //初始化点播client
    public DefaultAcsClient initVodClient(StsToken stsToken) {
        String regionId = "cn-beijing";  // 点播服务接入地域
        DefaultProfile profile = DefaultProfile.getProfile(regionId, stsToken.getAccessKeyId(), stsToken.getAccessKeySecret(), stsToken.getSecurityToken());
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }

    //获取播放地址函数
    public static GetPlayInfoResponse getPlayInfo(DefaultAcsClient client, String videoId) throws Exception {
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        request.setVideoId(videoId);
        return client.getAcsResponse(request);
    }

    @PostMapping("recommend")
    public ResultVo getRecommendVideo() {
        return ResultVo.error();
    }


    @PostMapping("getPlayInfo/{videoId}")
    public ResultVo getPlay(@RequestBody StsToken stsToken, @PathVariable String videoId) throws ClientException {
        DefaultAcsClient client = initVodClient(stsToken);
        GetPlayInfoResponse response = new GetPlayInfoResponse();
        String url = "";
        GetPlayInfoResponse.PlayInfo temp = null;
        try {
            response = getPlayInfo(client, videoId);
            List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
            //播放地址
            for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
                System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
                System.out.println("playInfo:" + playInfo.toString());
                temp = playInfo;
                url = playInfo.getPlayURL();
            }
            //Base信息
            System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");
        } catch (Exception e) {
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
        }
        System.out.print("RequestId = " + response.getRequestId() + "\n");
        //return ResultVo.ok().data("playUrl", url).data("temp", temp).data("videoBase",response.getVideoBase());
        return ResultVo.ok().data("playUrl", url);
    }


    @ApiOperation("添加视频")
    @PostMapping("/add")
    public ResultVo addVideo(@RequestBody Video video) {
        System.out.println("VIdeo" + video);
        videoService.save(video);
        return ResultVo.ok();
    }


    @ApiOperation("获取影片资料")
    @GetMapping("getVideo/{videoId}")
    public ResultVo getVideo(@PathVariable String videoId) throws ClientException {
        //初始化client对象
        DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(
                vodProperties.getKi(),
                vodProperties.getKs());
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        Video video = videoService.getVideoByVideoId(videoId);
        //1.设置请求
        request.setVideoId(videoId);
        //2.发送请求
        GetPlayInfoResponse response = client.getAcsResponse(request);
        try {
            List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
            //播放地址
            for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
                //3.根据videoId获取影片链接
                System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
                //如果第一次访问就把查找到的信息存储到数据库里
                if (video.getLinkCount() == 0) {
                    video.setWidth(Math.toIntExact(playInfo.getWidth()));
                    video.setHeight(Math.toIntExact(playInfo.getHeight()));
                    video.setDuration(playInfo.getDuration());
                }
                //影片url每次访问时更新
                video.setUrl(playInfo.getPlayURL());
            }

            //如果第一次访问就把查找到的信息存储到数据库里
            if (video.getLinkCount() == 0) {
                video.setTitle(response.getVideoBase().getTitle());
                video.setCover(response.getVideoBase().getCoverURL());
            }
            video.setLinkCount(video.getLinkCount() + 1);
            //更新影片数据
            videoService.updateById(video);
            //3.返回影片数据
            return ResultVo.ok().data("video", video);
        } catch (Exception e) {
            System.err.print("ErrorMessage = " + e.getLocalizedMessage());
            throw GlobalException.from(ResultCode.FILE_UPLOAD_ERROR);
        }
    }

    @ApiOperation("获取用户影片")
    @GetMapping("getUserVideoList/{userId}")
    public ResultVo getUserVideoList(@PathVariable String userId) {
        List<Video> videoList = videoService.getVideoListByUserId(userId);
        return ResultVo.ok().data("videoList", videoList);
    }


    @ApiOperation("删除影片")
    @DeleteMapping("delete/{videoId}")
    public void removeVideo(@PathVariable String videoId) {
        try {
            //初始化client对象
            DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(
                    vodProperties.getKi(),
                    vodProperties.getKs());

            //创建request对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            request.setVideoIds(videoId);

            //调用初始化方法进行删除
            client.getAcsResponse(request);
        } catch (Exception e) {
            e.printStackTrace();
            throw GlobalException.from("影片删除失败");
        }
    }


    @GetMapping("getAll")
    public ResultVo getAllVideo() {
        return ResultVo.ok().data("测试", "测试");
    }

    @GetMapping("getVideoList")
    public ResultVo getVideoList() {
        return null;
    }


}


