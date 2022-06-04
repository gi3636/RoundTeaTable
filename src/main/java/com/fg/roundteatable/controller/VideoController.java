package com.fg.roundteatable.controller;

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



    // @ApiOperation("上传影片")
    // @PostMapping("/upload/video")
    // public String uploadVideo(MultipartFile file) {
    //     Video video = new Video();
    //     SessionContext sessionContext = new SessionContext();
    //     JwtInfo jwtInfo = sessionContext.getJwtInfo();
    //     try {
    //         //1,获取文件
    //         String fileName = file.getOriginalFilename();
    //         //设置title  (上传成功以后显示的名字)
    //         String title = fileName.substring(0, fileName.lastIndexOf("."));
    //         InputStream inputStream = file.getInputStream();
    //         UploadStreamRequest request = new UploadStreamRequest(vodProperties.getKeyId(), vodProperties.getKeySecret(), title, fileName, inputStream);
    //         /* 点播服务接入点 */
    //         request.setApiRegionId("cn-beijing");
    //         //2.上传去服务器
    //         UploadVideoImpl uploader = new UploadVideoImpl();
    //         UploadStreamResponse response = uploader.uploadStream(request);
    //         if (response.isSuccess()) {
    //             //3.获得返回videoId
    //             System.out.println("上传的影片成功！VideoId:"+response.getVideoId());
    //             //4.存储数据
    //             video.setUserId(jwtInfo.getId());
    //             video.setVideoId(response.getVideoId());
    //             videoService.save(video);
    //             //5.返回videoId
    //             return response.getVideoId();
    //         } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
    //             System.out.print("VideoId=" + response.getVideoId() + "\n");
    //             System.out.print("ErrorCode=" + response.getCode() + "\n");
    //             System.out.print("ErrorMessage=" + response.getMessage() + "\n");
    //             log.error("阿里云上传失败：" + response.getVideoId() + "-" + response.getCode() + "-" + response.getMessage());
    //             return response.getVideoId();
    //         }
    //     } catch (Exception e) {
    //         log.error(e.getMessage());
    //         e.printStackTrace();
    //         return null;
    //     }
    // }


    @ApiOperation("获取影片资料")
    @GetMapping("getVideo/{videoId}")
    public ResultVo getVideo(@PathVariable String videoId) throws ClientException {
        //初始化client对象
        DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(
                vodProperties.getKeyId(),
                vodProperties.getKeySecret());
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
                if (video.getLinkCount() == 0 ){
                    video.setWidth(Math.toIntExact(playInfo.getWidth()));
                    video.setHeight(Math.toIntExact(playInfo.getHeight()));
                    video.setDuration(playInfo.getDuration());
                }
                //影片url每次访问时更新
                video.setUrl(playInfo.getPlayURL());
            }

            //如果第一次访问就把查找到的信息存储到数据库里
            if (video.getLinkCount() == 0){
                video.setTitle(response.getVideoBase().getTitle());
                video.setCover(response.getVideoBase().getCoverURL());
            }
            video.setLinkCount(video.getLinkCount()+1);
            //更新影片数据
            videoService.updateById(video);
            //3.返回影片数据
            return ResultVo.ok().data("video",video);
        } catch (Exception e) {
            System.err.print("ErrorMessage = " + e.getLocalizedMessage());
            throw GlobalException.from(ResultCode.FILE_UPLOAD_ERROR);
        }
    }

    @ApiOperation("获取用户影片")
    @GetMapping("getUserVideoList/{userId}")
    public ResultVo getUserVideoList(@PathVariable String userId){
        List<Video> videoList = videoService.getVideoListByUserId(userId);
        return ResultVo.ok().data("videoList",videoList);
    }



    @ApiOperation("删除影片")
    @DeleteMapping("delete/{videoId}")
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

    @GetMapping("getVideoList")
    public ResultVo getVideoList(){
        return null;
    }


}


