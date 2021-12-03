package com.zj.dingtalk.robot.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zj.dingtalk.robot.service.SystemParamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * SystemParamController.java
 * </p>
 *
 * @author zhoujian
 * @date 2021/12/03
 * @finished false
 */
@RequestMapping("/param")
@RestController
@Slf4j
public class SystemParamController {

    @Autowired
    SystemParamService systemParamService;

    @PostMapping("update")
    public String update(@RequestBody JSONObject config) {
        log.info("接收到参数更新请求, 参数: {}", JSON.toJSONString(config));

        for (Map.Entry<String, Object> entry : config.entrySet()) {
            systemParamService.update(entry.getKey(), entry.getValue().toString());
        }

        return "更新成功";
    }
}
