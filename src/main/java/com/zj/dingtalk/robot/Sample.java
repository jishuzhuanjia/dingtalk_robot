// This file is auto-generated, don't edit it. Thanks.
package com.zj.dingtalk.robot;

import com.alibaba.fastjson.JSON;
import com.aliyun.dingtalkrobot_1_0.models.BatchSendOTOHeaders;
import com.aliyun.dingtalkrobot_1_0.models.BatchSendOTORequest;
import com.aliyun.dingtalkrobot_1_0.models.BatchSendOTOResponse;
import com.aliyun.tea.*;
import com.aliyun.teautil.models.*;
import com.aliyun.teaopenapi.models.*;

import java.util.HashMap;
import java.util.Map;

public class Sample {
    
    /**
     * 使用 Token 初始化账号Client
     * @return Client
     * @throws Exception
     */
    public static com.aliyun.dingtalkrobot_1_0.Client createClient() throws Exception {
        Config config = new Config();
        config.protocol = "https";
        config.regionId = "central";
        return new com.aliyun.dingtalkrobot_1_0.Client(config);
    }
    
    public static void main(String[] args_) throws Exception {
        java.util.List<String> args = java.util.Arrays.asList(args_);
        com.aliyun.dingtalkrobot_1_0.Client client = Sample.createClient();
        BatchSendOTOHeaders batchSendOTOHeaders = new BatchSendOTOHeaders();
        batchSendOTOHeaders.xAcsDingtalkAccessToken = "5a3023a8cd8b314488741af74537b683";
        // 发送文本
        Map<String, String> param = new HashMap<>();
        param.put("content","种树吗?");
        BatchSendOTORequest batchSendOTORequest = new BatchSendOTORequest()
                .setRobotCode("dingyw7zqfyjjhqgywwx")
                .setUserIds(java.util.Arrays.asList(
                        // staffId
                        "all"
                ))
                .setMsgKey("sampleText")
                .setMsgParam(JSON.toJSONString(param));
        try {
            BatchSendOTOResponse response = client.batchSendOTOWithOptions(batchSendOTORequest, batchSendOTOHeaders, new RuntimeOptions());
            System.out.println(JSON.toJSONString(response));
        } catch (TeaException err) {
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
            }
            
        } catch (Exception _err) {
            TeaException err = new TeaException(_err.getMessage(), _err);
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
            }
        }
    }
}