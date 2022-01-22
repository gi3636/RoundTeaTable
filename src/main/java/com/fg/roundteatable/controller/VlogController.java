package com.fg.roundteatable.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.fg.roundteatable.common.GlobalException.GlobalException;
import com.fg.roundteatable.config.OssProperties;
import com.fg.roundteatable.config.VodProperties;
import com.fg.roundteatable.util.AliyunVodSDKUtils;
import com.fg.roundteatable.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Date;
import java.util.UUID;


/**
 * <p>
 * 短视频表 前端控制器
 * </p>
 *
 * @author FG
 * @since 2022-01-16
 */
@RestController
@RequestMapping("/vlog")
public class VlogController {
    /**
     * 实现Oss的文件上传
     *
     * @param file
     * @return
     */
    @Autowired
    private OssProperties ossProperties;

    @Autowired
    private VodProperties vodProperties;

    @GetMapping("/test")
    public String test() {
        return ossProperties.toString();
    }

    @PostMapping("/upload/file")
    public String uploadFile(MultipartFile file) {
        // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
        String endpoint = OssProperties.END_POINT;
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = OssProperties.KEY_ID;
        String accessKeySecret = OssProperties.KEY_SECRET;
        String bucketName = OssProperties.BUCKET_NAME;

        //获取上传文件的地址
        String filename = file.getOriginalFilename();

        //设置文件的名字为唯一值
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        filename = uuid + filename;

        //把文件按照日期来进行分类
        String datePath = DateUtil.COMMON_FULL.getDateText(new Date());

        filename = datePath + "/" + filename;

        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            // 填写本地文件的完整路径。如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
            InputStream inputStream = file.getInputStream();
            // 填写Bucket名称和Object完整路径。Object完整路径中不能包含Bucket名称。
            //第二个参数是文件名称
            ossClient.putObject(bucketName, filename, inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();

            //返回文件上传的地址，需要自己手动拼接字符串
            //  https://edu-online-avatars.oss-cn-chengdu.aliyuncs.com/avatar/cc2.jpg
            String url = "https://" + bucketName + "." + endpoint + "/" + filename;
            return url;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/test1")
    public String test1() {
        return vodProperties.toString();
    }

    @PostMapping("/upload/video")
    public String uploadVideo(MultipartFile file) {
        DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(
                vodProperties.getKeyId(),
                vodProperties.getKeySecret());
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
                System.out.print("VideoId=" + response.getVideoId() + "\n");
                return response.getVideoId();
            } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
                System.out.print("VideoId=" + response.getVideoId() + "\n");
                System.out.print("ErrorCode=" + response.getCode() + "\n");
                System.out.print("ErrorMessage=" + response.getMessage() + "\n");
                return response.getVideoId();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
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
        }catch (Exception e){
            e.printStackTrace();
            throw GlobalException.from("影片删除失败");
        }
    }

}


