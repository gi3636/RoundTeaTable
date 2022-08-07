package com.fg.roundteatable.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;
import com.fg.roundteatable.common.GlobalException.GlobalException;
import com.fg.roundteatable.common.ResultCode.ResultCode;
import com.fg.roundteatable.common.ResultVo;
import com.fg.roundteatable.config.StsProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/sts")
@RestController
public class StsController {

    @Autowired
    private StsProperties stsProperties;

    @GetMapping("token")
    public ResultVo getStsToken() {
        // STS接入地址，例如sts.cn-hangzhou.aliyuncs.com。
        String endpoint = stsProperties.getEndpoint();
        String AccessKeyId = stsProperties.getKi();
        String accessKeySecret = stsProperties.getKs();
        // 填写步骤3获取的角色ARN。
        String roleArn = stsProperties.getRoleArn();
        // 自定义角色会话名称，用来区分不同的令牌，例如可填写为SessionTest。
        String roleSessionName = "SessionTest";
        try {
            // regionId表示RAM的地域ID。以华东1（杭州）地域为例，regionID填写为cn-hangzhou。也可以保留默认值，默认值为空字符串（""）。
            String regionId = "";
            // 添加endpoint。适用于Java SDK 3.12.0及以上版本。
            DefaultProfile.addEndpoint(regionId, "Sts", endpoint);
            IClientProfile profile = DefaultProfile.getProfile(regionId, AccessKeyId, accessKeySecret);
            DefaultAcsClient client = new DefaultAcsClient(profile);
            final AssumeRoleRequest request = new AssumeRoleRequest();
            request.setSysMethod(MethodType.POST);
            request.setRoleArn(roleArn);
            request.setRoleSessionName(roleSessionName);
            //request.setPolicy(policy); // 如果policy为空，则用户将获得该角色下所有权限。
            request.setDurationSeconds(3600L); // 设置临时访问凭证的有效时间为3600秒。
            final AssumeRoleResponse response = client.getAcsResponse(request);
            return ResultVo.ok().data("credentials", response.getCredentials());
        } catch (ClientException e) {
            throw GlobalException.from(ResultCode.GET_STS_TOKEN_ERROR);
        }
    }


}
