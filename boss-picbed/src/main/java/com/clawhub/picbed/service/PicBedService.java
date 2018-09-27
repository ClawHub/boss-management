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
     * 根据类型获取图片信息
     *
     * @param type 类型
     * @return 图片信息
     */
    List<PicBed> queryPic(String type);
}
