package com.zj.dingtalk.robot.service;

import com.alibaba.fastjson.JSON;
import com.zj.dingtalk.robot.entity.SystemParam;
import com.zj.dingtalk.robot.mapper.SystemParamMapper;
import com.zj.util.commons.lang.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统参数服务
 * </p>
 *
 * @author zhoujian
 * @date 2021/12/03
 * @finished false
 */
@Service
@Slf4j
public class SystemParamService {

    @Autowired
    SystemParamMapper systemParamMapper;

    /**
     * 参数
     */
    private Map<String, String> paramMap = new HashMap<>();

    /**
     * 初始化参数
     */
    @PostConstruct
    public void load() {
        List<SystemParam> systemParams = systemParamMapper.selectAll();

        for (SystemParam sp : systemParams) {
            paramMap.put(sp.getParamName(), sp.getValue());
        }
        log.info("load system config from datasource success, new config: {}", JSON.toJSONString(paramMap));
    }

    public @Nullable
    String getParam(String key) {
        return paramMap.get(key);
    }

    @Scheduled(cron = "0 0/5 * * * *")
    public void reload() {
        log.info("start to reload system config...");
        load();
    }

    /**
     * 更新参数立即生效
     */
    public void update(String key, String value) {
        if (StringUtils.isBlank(value)) {
            throw new IllegalArgumentException("新配置不能为空");
        }

        paramMap.put(key, value);

        Example example = new Example(SystemParam.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("paramName",key);

        SystemParam updatePO = new SystemParam();
        updatePO.setValue(value);

        systemParamMapper.updateByExampleSelective(updatePO, example);
    }
}
