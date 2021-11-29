package com.zj.dingtalk.robot.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.page.PageMethod;
import com.zj.dingtalk.robot.entity.Image;
import com.zj.dingtalk.robot.mapper.ImageMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * <p>
 * ImageService.java
 * </p>
 *
 * @author zhoujian
 * @date 2021/11/29
 * @finished false
 */
@Service
@Slf4j
public class ImageService {

    @Autowired
    private ImageMapper imageMapper;

    /**
     * 随即返回一张网络图片地址
     */
    public String getRandomImageUrl() {
        log.info("正在随机返回一张图片");

        // 查询最大id
        Page<Image> page = PageMethod.startPage(1, 1).setOrderBy("id desc").doSelectPage(
                () -> {
                    imageMapper.selectAll();
                }
        );

        int maxId = page.getResult().get(0).getId();
        log.debug("图片最大id: {}", maxId);

        int randomId = new Random().nextInt(maxId) + 1;
        log.info("随机图片, id: {}",randomId);

        Image image = new Image();
        image.setId(randomId);

        return imageMapper.selectOne(image).getUrl();
    }
}
