package com.fg.roundteatable.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.fg.roundteatable.common.GlobalException.GlobalException;
import com.fg.roundteatable.common.ResultCode.ResultCode;
import com.fg.roundteatable.common.ResultVo;
import com.fg.roundteatable.config.StsProperties;
import com.fg.roundteatable.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

@Slf4j
@RequestMapping("file")
public class FileController {
    @Autowired
    private StsProperties stsProperties;

    @GetMapping("/test")
    public String test() {
        return stsProperties.toString();
    }

    @PostMapping("/upload/file")
    public String uploadFile(MultipartFile file) {
        // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
        String endpoint = StsProperties.END_POINT;
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = StsProperties.KEY_ID;
        String accessKeySecret = StsProperties.KEY_SECRET;
        String bucketName = StsProperties.BUCKET_NAME;

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
            log.error(e.getMessage());
            throw new GlobalException(ResultCode.FILE_UPLOAD_ERROR);
        }
    }


    @GetMapping("getAll")
    public ResultVo getAllFile(){
        return ResultVo.ok().data("测试","测试");
    }

    @DeleteMapping("delete/{id}")
    public ResultVo deleteFile(@PathVariable String id){
        return ResultVo.ok();
    }


    @DeleteMapping("delete")
    public ResultVo deleteFiles(){
        return ResultVo.ok();
    }

}
