package com.zj;

import com.zj.dingtalk.robot.Application;
import com.zj.dingtalk.robot.mapper.DingtalkGroupMapper;
import com.zj.dingtalk.robot.service.ImageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p>
 * test.java
 * </p>
 *
 * @author zhoujian
 * @date 2021/11/29
 * @finished false
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class test {

    @Autowired
    DingtalkGroupMapper dingtalkGroupMapper;
    
    @Autowired
    ImageService imageService;

    @Test
    public void test(){

        System.out.println("�����ɹ�");

        imageService.getRandomImageUrl();
    }
}
