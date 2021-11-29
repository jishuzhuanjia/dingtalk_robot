package com.zj.dingtalk.robot;

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
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
