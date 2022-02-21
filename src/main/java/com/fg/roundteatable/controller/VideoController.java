package com.fg.roundteatable.controller;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.fg.roundteatable.common.GlobalException.GlobalException;
import com.fg.roundteatable.common.ResultCode.ResultCode;
import com.fg.roundteatable.common.ResultVo;
import com.fg.roundteatable.config.VodProperties;
import com.fg.roundteatable.entity.Video;
import com.fg.roundteatable.service.VideoService;
import com.fg.roundteatable.util.AliyunVodSDKUtils;
import com.fg.roundteatable.util.SessionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
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


    @GetMapping("/test1")
    public String test1() {
        return vodProperties.toString();
    }

    @PostMapping("/upload/video")
    public String uploadVideo(MultipartFile file) {
        Video video = new Video();
        SessionContext sessionContext = new SessionContext();
        //JwtInfo jwtInfo = sessionContext.getJwtInfo();
        try {
            //设置文件名字
            String fileName = file.getOriginalFilename();
            //设置title  (上传成功以后显示的名字)
            String title = fileName.substring(0, fileName.lastIndexOf("."));
            //设置inputStream
            InputStream inputStream = file.getInputStream();
            UploadStreamRequest request = new UploadStreamRequest(vodProperties.getKeyId(), vodProperties.getKeySecret(), title, fileName, inputStream);
            /* 点播服务接入点 */
            request.setApiRegionId("cn-beijing");
            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            if (response.isSuccess()) {
                //上传成功
                System.out.println("response"+ response);
                System.out.print("VideoId = " + response.getVideoId() + "\n");
                //存储数据
                //video.setUserId(jwtInfo.getId());
                video.setVideoId(response.getVideoId());
                getVideo(response.getVideoId());
                return response.getVideoId();
            } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
                System.out.print("VideoId=" + response.getVideoId() + "\n");
                System.out.print("ErrorCode=" + response.getCode() + "\n");
                System.out.print("ErrorMessage=" + response.getMessage() + "\n");
                log.error("阿里云上传失败：" + response.getVideoId() + "-" + response.getCode() + "-" + response.getMessage());
                return response.getVideoId();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }


    @GetMapping("getVideo/{videoId}")
    public GetPlayInfoResponse.VideoBase getVideo(@PathVariable String videoId) throws ClientException {
        //初始化client对象
        DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(
                vodProperties.getKeyId(),
                vodProperties.getKeySecret());
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        request.setVideoId(videoId);
        GetPlayInfoResponse response = client.getAcsResponse(request);
        Video video = videoService.getVideoByVideoId(videoId);

        try {
            String url = "";
            List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
            //播放地址
            for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
                System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
                url = playInfo.getPlayURL();
            }
            if (video == null){
                video.setVideoId(videoId);
            }else{
                video.setUrl(url);
            }
            System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");

            return response.getVideoBase();
        } catch (Exception e) {
            System.err.print("ErrorMessage = " + e.getLocalizedMessage());
            throw GlobalException.from(ResultCode.FILE_UPLOAD_ERROR);
        }
    }

    @GetMapping("getVideoList")
    public ResultVo getVideoList(){
        return null;
    }

    @GetMapping("getAuth")
    public ResultVo getAuth() {
        return null;
    }


    @PostMapping("delete/{videoId}")
    public void removeVideo(@PathVariable String videoId) {
        try {
            //初始化client对象
            DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(
                    vodProperties.getKeyId(),
                    vodProperties.getKeySecret());

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


    @DeleteMapping("delete")
    public ResultVo deleteVideo() {
        return ResultVo.ok();
    }


}


