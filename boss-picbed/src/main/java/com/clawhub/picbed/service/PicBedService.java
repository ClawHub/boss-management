package com.clawhub.picbed.service;

import com.clawhub.picbed.entity.PicBed;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 图床服务
 */
public interface PicBedService {
    /**
     * 图片上传
     *
     * @param map 图片信息
     * @return url
     * @throws IOException
     * @throws InterruptedException
     */
    String upload(Map<String, MultipartFile> map) throws IOException, InterruptedException;

    /**
     * 根据标签获取图片信息
     *
     * @param tags 标签
     * @return 图片信息
     */
    List<PicBed> queryPic(String tags);

    /**
     * 上传图片
     *
     * @param image 图片base64
     * @param title 标题
     * @param alt   图片替换文字
     * @param tags  标签
     * @return 图片地址
     */
    String upload(String image, String title, String alt, String tags) throws IOException;

    /**
     * 刷新图床
     *
     * @return 进度
     */
    String refreshPicBed() throws IOException, InterruptedException;
}
