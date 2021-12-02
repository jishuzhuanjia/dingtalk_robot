package com.zj.dingtalk.robot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * <p>
 * Application.java
 * </p>
 *
 * @author zhoujian
 * @date 2021/11/29
 * @finished false
 */
@SpringBootApplication
@EnableScheduling
@MapperScan(basePackages = "com.zj.dingtalk.robot.mapper")
@Slf4j
public class Application {

    /**
     * 启动参数列表
     */
    public static void main(String[] args) {
        log.info("dingtalk robot application server is starting...");
        SpringApplication.run(Application.class,args);
    }
}
